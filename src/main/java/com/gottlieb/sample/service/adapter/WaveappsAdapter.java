package com.gottlieb.sample.service.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gottlieb.sample.service.dto.InvoiceRequestDTO;
import com.gottlieb.sample.service.dto.InvoiceResponseDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service("waveapps")
public class WaveappsAdapter implements InvoiceAdapterInterface {

    private static final Logger LOG = LoggerFactory.getLogger(WaveappsAdapter.class);

    @Value("${apis.invoices.waveapps.url}")
    private String waveappsUrl;

    @Value("${apis.invoices.waveapps.token}")
    private String apiToken;

    @Value("${apis.invoices.waveapps.business-id}")
    private String businessId;

    @Override
    public Mono<InvoiceResponseDTO> createInvoice(InvoiceRequestDTO request) {
        LOG.debug("WaveappsAdapter - creating invoice for request: {}", request);

        String customerId = request.getAccount().getGeneralLedgerId();
        String invoiceNumber = request.getReferenceNumber();

        String query =
            "mutation ($input: InvoiceCreateInput!) { invoiceCreate(input: $input) { didSucceed inputErrors { message code path } invoice { id pdfUrl status total { value currency { symbol } } } } }";

        List<WaveappsItem> items = request
            .getItems()
            .stream()
            .map(v2Item ->
                new WaveappsItem(v2Item.getProduct().getGeneralLedgerId(), v2Item.getQuantity(), v2Item.getUnitPrice().doubleValue())
            )
            .collect(Collectors.toList());

        Map<String, Object> input = Map.of(
            "businessId",
            businessId,
            "customerId",
            customerId,
            "items",
            items,
            "invoiceNumber",
            invoiceNumber
        );

        Map<String, Object> payload = Map.of("query", query, "variables", Map.of("input", input));

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String jsonPayload = objectMapper.writeValueAsString(payload);
            LOG.debug("jsonPayload {}", jsonPayload);

            InvoiceResponseDTO businessResponse = new InvoiceResponseDTO();

            Mono<WaveappsCreateInvoiceResponse> rawResponse = createWebClient()
                .post()
                .bodyValue(jsonPayload)
                .retrieve()
                .bodyToMono(WaveappsCreateInvoiceResponse.class);

            return rawResponse
                .doOnError(error -> LOG.error("Error occurred while creating invoice: {}", error.getMessage()))
                .onErrorResume(error -> Mono.error(new RuntimeException("WaveApps API call failed", error)))
                .flatMap(resp -> {
                    if (
                        resp.getData() != null &&
                        resp.getData().getInvoiceCreate() != null &&
                        resp.getData().getInvoiceCreate().isDidSucceed()
                    ) {
                        businessResponse.setInvoiceId(resp.getData().getInvoiceCreate().getInvoice().getId());
                        businessResponse.setDocumentUrl(resp.getData().getInvoiceCreate().getInvoice().getPdfUrl());
                        businessResponse.setInvoiceStatus(resp.getData().getInvoiceCreate().getInvoice().getStatus());
                        return Mono.just(businessResponse);
                    } else {
                        String errorMessages = consolidateErrors(resp);
                        LOG.error("Failed to create invoice. Errors: {}", errorMessages);
                        return Mono.error(new RuntimeException("Failed to create invoice: " + errorMessages));
                    }
                });
        } catch (JsonProcessingException e) {
            LOG.error("Failed to process JSON payload", e);
            return Mono.error(new RuntimeException("Failed to process JSON payload", e));
        }
    }

    private WebClient createWebClient() {
        return WebClient.builder()
            .baseUrl(waveappsUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiToken)
            .build();
    }

    private String consolidateErrors(WaveappsCreateInvoiceResponse response) {
        StringBuilder errorMessages = new StringBuilder();

        if (response.getErrors() != null && !response.getErrors().isEmpty()) {
            response
                .getErrors()
                .forEach(error -> {
                    errorMessages.append("Error: ").append(error.getMessage()).append("\n");
                });
        }

        if (response.getData() != null && response.getData().getInvoiceCreate() != null) {
            if (response.getData().getInvoiceCreate().getInputErrors() instanceof List<?>) {
                List<?> inputErrors = (List<?>) response.getData().getInvoiceCreate().getInputErrors();
                inputErrors.forEach(inputError -> {
                    if (inputError instanceof Map) {
                        Object message = ((Map<?, ?>) inputError).get("message");
                        if (message != null) {
                            errorMessages.append("Input Error: ").append(message).append("\n");
                        }
                    }
                });
            }
        }

        return errorMessages.toString().isEmpty() ? "Unknown error occurred." : errorMessages.toString();
    }
}
