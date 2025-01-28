package com.gottlieb.sample.web.rest;

import com.gottlieb.sample.service.InvoiceService;
import com.gottlieb.sample.service.dto.InvoiceRequestDTO;
import com.gottlieb.sample.service.dto.InvoiceResponseDTO;
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

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceService invoiceService;

    public InvoiceResource(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    /**
     * {@code CREATE /invoices}
     *
     * @param json the invoiceDTO to create request.
     * @return the {@link InvoiceResponseDTO} with status {@code 200}.
     */
    @PostMapping("/create")
    public Mono<ResponseEntity<InvoiceResponseDTO>> createInvoice(@RequestBody InvoiceRequestDTO request) {
        return invoiceService.createInvoice(request).map(response -> ResponseEntity.ok(response));
    }
}
