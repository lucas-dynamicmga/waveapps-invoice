package com.gottlieb.sample.service.adapter;

import com.gottlieb.sample.service.dto.InvoiceRequestDTO;
import com.gottlieb.sample.service.dto.InvoiceResponseDTO;

import reactor.core.publisher.Mono;

public interface InvoiceAdapter {
    Mono<InvoiceResponseDTO> createInvoice(InvoiceRequestDTO request);
}