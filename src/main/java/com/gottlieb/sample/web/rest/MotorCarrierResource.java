package com.gottlieb.sample.web.rest;

import com.gottlieb.sample.service.MotorCarrierService;
import com.gottlieb.sample.service.dto.MotorCarrierDriverResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierGeneralResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINRequestDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVINResponseDTO;
import com.gottlieb.sample.service.dto.MotorCarrierVehicleResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing {@link com.gottlieb.sample.domain.CAB}.
 */
@RestController
@RequestMapping("/api/motor-carrier")
@Validated
public class MotorCarrierResource {

    private final MotorCarrierService motorCarrierService;

    public MotorCarrierResource(MotorCarrierService motorCarrierService) {
        this.motorCarrierService = motorCarrierService;
    }

    /**
     * {@code POST /motor-carrier/get}
     *
     * @param request the MotorCarrierRequestDTO containing the request data.
     * @return the {@link MotorCarrierResponseDTO} with status {@code 200}.
     */
    @Operation(
        summary = "Retrieve general data",
        description = "Fetches transport analysis data based on a given DOT number.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MotorCarrierRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Get transport analysis",
                        summary = "dotNumber: DOT Number",
                        value = "{\"dotNumber\": \"2506853\"}"
                    ),
                }
            )
        )
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = MotorCarrierGeneralResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/problem+json")),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(mediaType = "application/problem+json")
            ),
        }
    )
    @PostMapping("/dot")
    public Mono<MotorCarrierGeneralResponseDTO> getDOT(@Valid @RequestBody MotorCarrierRequestDTO request) {
        return motorCarrierService.getDOT(request);
    }

    @Operation(
        summary = "Retrieve driver data",
        description = "Fetches transport analysis data based on a given DOT number.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MotorCarrierRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Get transport analysis",
                        summary = "dotNumber: DOT Number",
                        value = "{\"dotNumber\": \"2506853\"}"
                    ),
                }
            )
        )
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = MotorCarrierGeneralResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/problem+json")),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(mediaType = "application/problem+json")
            ),
        }
    )
    @PostMapping("/driver")
    public Mono<MotorCarrierDriverResponseDTO> getDriver(@Valid @RequestBody MotorCarrierRequestDTO request) {
        return motorCarrierService.getDriver(request);
    }

    @Operation(
        summary = "Retrieve vehicle data",
        description = "Fetches transport analysis data based on a given DOT number.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MotorCarrierRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Get transport analysis",
                        summary = "dotNumber: DOT Number",
                        value = "{\"dotNumber\": \"2506853\"}"
                    ),
                }
            )
        )
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = MotorCarrierGeneralResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/problem+json")),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(mediaType = "application/problem+json")
            ),
        }
    )
    @PostMapping("/vehicle")
    public Mono<MotorCarrierVehicleResponseDTO> getVehicle(@Valid @RequestBody MotorCarrierRequestDTO request) {
        return motorCarrierService.getVehicle(request);
    }

    @Operation(
        summary = "Retrieve vehicles data by VIN",
        description = "Fetches vehicles data based on a given VIN Number.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = MotorCarrierRequestDTO.class),
                examples = {
                    @ExampleObject(
                        name = "Get transport analysis",
                        summary = "vin: VIN Number",
                        value = "{\"vin\": \"1XKYD49X6GJ474462\"}"
                    ),
                }
            )
        )
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Successful operation",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = MotorCarrierGeneralResponseDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(mediaType = "application/problem+json")),
            @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(mediaType = "application/problem+json")
            ),
        }
    )
    @PostMapping("/vin")
    public Mono<MotorCarrierVINResponseDTO> getVehicleByVIN(@Valid @RequestBody MotorCarrierVINRequestDTO request) {
        return motorCarrierService.getVehicleByVIN(request);
    }
}
