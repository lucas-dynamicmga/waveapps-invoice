package com.gottlieb.sample.web.rest;

import com.gottlieb.sample.service.InvoiceService;
import com.gottlieb.sample.service.dto.InvoiceRequestDTO;
import com.gottlieb.sample.service.dto.InvoiceResponseDTO;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing {@link com.gottlieb.sample.domain.Invoice}.
 */
@RestController
@RequestMapping("/api/invoices")
public class InvoiceResource {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceResource.class);

    private static final String ENTITY_NAME = "invoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceService invoiceService;

    public InvoiceResource(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * {@code CREATE  /invoices/:id} : delete the "id" invoice.
     *
     * @param id the id of the invoiceDTO to delete.
     * @return the {@link InvoiceResponseDTO} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/create")
    public Mono<ResponseEntity<InvoiceResponseDTO>> createInvoice(@RequestBody InvoiceRequestDTO request) {
        return invoiceService.createInvoice(request).map(response -> ResponseEntity.ok(response));
        //             .onErrorResume(e -> Mono.just(
        //                 ResponseEntity.status(HttpStatus.SC_METHOD_FAILURE)
        //                     .body(Map.of("error", e.getMessage()))
        //             ));
    }
}
