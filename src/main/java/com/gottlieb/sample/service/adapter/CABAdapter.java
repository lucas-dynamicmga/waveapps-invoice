package com.gottlieb.sample.service.adapter;

import com.gottlieb.sample.service.adapter.cab.CABDriverResponse;
import com.gottlieb.sample.service.adapter.cab.CABGeneralResponse;
import com.gottlieb.sample.service.adapter.cab.CABVINResponse;
import com.gottlieb.sample.service.adapter.cab.CABVehicleResponse;
import com.gottlieb.sample.service.dto.MotorCarrierDriverResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierGeneralResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVehicleResponseDTO;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralContact;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralMCS150;
import com.gottlieb.sample.service.dto.motorCarrier.MotorCarrierGeneralScores;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service("cab")
public class CABAdapter implements MotorCarrierAdapterInterface {

    private static final Logger logger = LoggerFactory.getLogger(CABAdapter.class);

    @Value("${apis.motorCarrier.cab.base-url}")
    private String CABBaseUrl;

    @Value("${apis.motorCarrier.cab.key}")
    private String CABKey;

    @Value("${apis.motorCarrier.cab.vin-base-url}")
    private String vinBaseUrl;

    private final WebClient webClient;

    CABAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<MotorCarrierGeneralResponseDTO> getDOT(MotorCarrierRequestDTO request) {
        try {
            return webClient
                .get()
                .uri(CABBaseUrl + request.getDotNumber() + "/general?key=" + CABKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CABGeneralResponse.class)
                .flatMap(cabResponse -> {
                    if (isResponseInvalid(cabResponse)) {
                        logger.error("Invalid response for DOT number {}: {}", request.getDotNumber(), cabResponse);
                        return Mono.error(new CustomNotFoundException("DOT passed incorrect or no information recorded"));
                    }
                    return Mono.just(mapToGeneralMotorCarrierResponseDTO(cabResponse));
                })
                .onErrorResume(e -> {
                    if (e instanceof CustomNotFoundException) {
                        logger.error("Custom not found exception for DOT number {}: {}", request.getDotNumber(), e.getMessage());
                        return Mono.error(e);
                    } else {
                        logger.error("Error calling CAB API: {}", e.getMessage());
                        return Mono.error(new RuntimeException("Error processing data from CAB API: " + e.getMessage()));
                    }
                });
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            return Mono.error(new RuntimeException("Unexpected error occurred: " + e.getMessage()));
        }
    }

    private boolean isResponseInvalid(CABGeneralResponse cabResponse) {
        return (
            cabResponse == null ||
            ((cabResponse.getContact() == null || isContactEmpty(cabResponse.getContact())) &&
                (cabResponse.getScores() == null || isScoresEmpty(cabResponse.getScores())) &&
                (cabResponse.getDockets() == null || cabResponse.getDockets().isEmpty()) &&
                (cabResponse.getMcs150() == null || isMcs150Empty(cabResponse.getMcs150())) &&
                (cabResponse.getYIB() == null || cabResponse.getYIB() == 0))
        );
    }

    private boolean isContactEmpty(MotorCarrierGeneralContact contact) {
        return (
            (contact.getAddresses() == null || contact.getAddresses().isEmpty()) &&
            (contact.getEmails() == null || contact.getEmails().isEmpty()) &&
            (contact.getNames() == null || contact.getNames().isEmpty()) &&
            (contact.getPhones() == null || contact.getPhones().isEmpty())
        );
    }

    private boolean isScoresEmpty(MotorCarrierGeneralScores scores) {
        return (scores.getBasics() == null || scores.getBasics().isEmpty());
    }

    private boolean isMcs150Empty(MotorCarrierGeneralMCS150 mcs150) {
        return (
            (mcs150.getDate() == null || mcs150.getDate().isEmpty()) &&
            (mcs150.getMileage() == null || mcs150.getMileage() == 0) &&
            (mcs150.getStatus() == null || mcs150.getStatus().isEmpty())
        );
    }

    private MotorCarrierGeneralResponseDTO mapToGeneralMotorCarrierResponseDTO(CABGeneralResponse cabResponse) {
        MotorCarrierGeneralResponseDTO responseDTO = new MotorCarrierGeneralResponseDTO();

        responseDTO.setContact(cabResponse.getContact());
        responseDTO.setScores(cabResponse.getScores());
        responseDTO.setDockets(cabResponse.getDockets());
        responseDTO.setMCS150(cabResponse.getMcs150());
        responseDTO.setYIB(cabResponse.getYIB());

        return responseDTO;
    }

    @Override
    public Mono<MotorCarrierDriverResponseDTO> getDriver(MotorCarrierRequestDTO request) {
        try {
            return webClient
                .get()
                .uri(CABBaseUrl + request.getDotNumber() + "/driver?key=" + CABKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(CABDriverResponse.class)
                .collectList()
                .flatMap(cabResponses -> {
                    if (cabResponses.isEmpty() || isResponseEmptyOrDefault(cabResponses)) {
                        logger.error("DOT number {} provided is incorrect. Response is empty or invalid.", request.getDotNumber());
                        return Mono.error(new CustomNotFoundException("DOT passado incorreto"));
                    }
                    return Mono.just(mapToDriverMotorCarrierResponseDTOList(cabResponses));
                })
                .onErrorResume(e -> {
                    logger.error("Error calling CAB API: {}", e.getMessage());
                    return Mono.error(new RuntimeException("Error processing data from CAB API: " + e.getMessage()));
                });
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            return Mono.error(new RuntimeException("Unexpected error occurred: " + e.getMessage()));
        }
    }

    private MotorCarrierDriverResponseDTO mapToDriverMotorCarrierResponseDTOList(List<CABDriverResponse> cabResponses) {
        MotorCarrierDriverResponseDTO responseDTO = new MotorCarrierDriverResponseDTO();

        responseDTO.setLicSt(cabResponses.stream().map(CABDriverResponse::getLicSt).findFirst().orElse("Unknown"));
        responseDTO.setInsp(cabResponses.stream().mapToInt(CABDriverResponse::getInsp).sum());
        responseDTO.setVios(cabResponses.stream().mapToInt(CABDriverResponse::getVios).sum());
        responseDTO.setDriverVios(cabResponses.stream().mapToInt(CABDriverResponse::getDriverVios).sum());
        responseDTO.setOos(cabResponses.stream().mapToInt(CABDriverResponse::getOos).sum());
        responseDTO.setTimeSpent(cabResponses.stream().mapToInt(CABDriverResponse::getTimeSpent).sum());
        responseDTO.setCrashes(cabResponses.stream().mapToInt(CABDriverResponse::getCrashes).sum());
        responseDTO.setInj(cabResponses.stream().mapToInt(CABDriverResponse::getInj).sum());
        responseDTO.setFat(cabResponses.stream().mapToInt(CABDriverResponse::getFat).sum());
        responseDTO.setTow(cabResponses.stream().mapToInt(CABDriverResponse::getTow).sum());

        return responseDTO;
    }

    private boolean isResponseEmptyOrDefault(List<CABDriverResponse> cabResponses) {
        return cabResponses
            .stream()
            .allMatch(
                response ->
                    response.getLicSt() == null ||
                    (response.getLicSt().isEmpty() &&
                        response.getInsp() == 0 &&
                        response.getVios() == 0 &&
                        response.getDriverVios() == 0 &&
                        response.getOos() == 0 &&
                        response.getTimeSpent() == 0 &&
                        response.getCrashes() == 0 &&
                        response.getInj() == 0 &&
                        response.getFat() == 0 &&
                        response.getTow() == 0)
            );
    }

    public class CustomNotFoundException extends ResponseStatusException {

        public CustomNotFoundException(String reason) {
            super(HttpStatus.NOT_FOUND, reason);
        }
    }

    @Override
    public Mono<MotorCarrierVehicleResponseDTO> getVehicle(MotorCarrierRequestDTO request) {
        try {
            return webClient
                .get()
                .uri(CABBaseUrl + request.getDotNumber() + "/vehicles?key=" + CABKey)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CABVehicleResponse.class)
                .flatMap(cabResponse -> {
                    if (isResponseInvalid(cabResponse)) {
                        logger.error("Invalid response for DOT number {}: {}", request.getDotNumber(), cabResponse);
                        return Mono.error(new CustomNotFoundException("DOT passado incorreto ou sem veículos registrados"));
                    }
                    return Mono.just(mapToVehicleMotorCarrierResponseDTO(cabResponse));
                })
                .onErrorResume(e -> {
                    logger.error("Error calling CAB API: {}", e.getMessage());
                    return Mono.error(new RuntimeException("Error processing data from CAB API: " + e.getMessage()));
                });
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            return Mono.error(new RuntimeException("Unexpected error occurred: " + e.getMessage()));
        }
    }

    private MotorCarrierVehicleResponseDTO mapToVehicleMotorCarrierResponseDTO(CABVehicleResponse cabResponse) {
        MotorCarrierVehicleResponseDTO responseDTO = new MotorCarrierVehicleResponseDTO();

        responseDTO.setPu(cabResponse.getPu());
        responseDTO.setNonPU(cabResponse.getNonPU());
        responseDTO.setUnknown(cabResponse.getUnknown());

        return responseDTO;
    }

    private boolean isResponseInvalid(CABVehicleResponse cabResponse) {
        return (
            cabResponse == null ||
            ((cabResponse.getPu() == null || cabResponse.getPu().isEmpty()) &&
                (cabResponse.getNonPU() == null || cabResponse.getNonPU().isEmpty()) &&
                (cabResponse.getUnknown() == null || cabResponse.getUnknown().isEmpty()))
        );
    }

    @Override
    public Mono<MotorCarrierVINResponseDTO> getVehicleByVIN(MotorCarrierVINRequestDTO request) {
        try {
            String CABUrl = vinBaseUrl + request.getVin() + "?format=json";

            return webClient
                .get()
                .uri(CABUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CABVINResponse.class)
                .flatMap(this::validateAndMapResponse)
                .onErrorResume(e -> {
                    logger.error("Error processing CAB API response: {}", e.getMessage());
                    return Mono.error(new RuntimeException("Failed to retrieve valid data from CAB API: " + e.getMessage()));
                });
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            return Mono.error(new RuntimeException("Unexpected error occurred: " + e.getMessage()));
        }
    }

    private Mono<MotorCarrierVINResponseDTO> validateAndMapResponse(CABVINResponse cabResponse) {
        if (cabResponse == null || cabResponse.getResults() == null || cabResponse.getResults().isEmpty()) {
            logger.error("CAB API response is empty or null");
            return Mono.error(new RuntimeException("Empty or null response from CAB API."));
        }

        if (cabResponse.getCount() <= 0) {
            logger.error("CAB API response has no valid entries");
            return Mono.error(new RuntimeException("No valid data found in CAB API response."));
        }

        if (cabResponse.getMessage() != null && cabResponse.getMessage().contains("NOTE:")) {
            logger.warn("CAB API response contains a note: {}", cabResponse.getMessage());
        }

        boolean hasEssentialData = cabResponse
            .getResults()
            .stream()
            .anyMatch(result -> "Make".equals(result.getVariable()) && result.getValue() != null && !result.getValue().isEmpty());

        if (!hasEssentialData) {
            logger.error("CAB API response is missing essential data in results");
            return Mono.error(new RuntimeException("Essential data is missing in CAB API response."));
        }

        return Mono.just(mapToVINMotorCarrierResponseDTO(cabResponse));
    }

    private MotorCarrierVINResponseDTO mapToVINMotorCarrierResponseDTO(CABVINResponse cabResponse) {
        MotorCarrierVINResponseDTO responseDTO = new MotorCarrierVINResponseDTO();

        responseDTO.setCount(cabResponse.getCount());
        responseDTO.setMessage(cabResponse.getMessage());
        responseDTO.setResults(cabResponse.getResults());
        responseDTO.setSearchCriteria(cabResponse.getSearchCriteria());

        return responseDTO;
    }
}
