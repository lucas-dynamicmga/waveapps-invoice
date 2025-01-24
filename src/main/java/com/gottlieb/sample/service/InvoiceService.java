package com.gottlieb.sample.service;

import com.gottlieb.sample.service.adapter.InvoiceAdapterInterface;
import com.gottlieb.sample.service.dto.InvoiceRequestDTO;
import com.gottlieb.sample.service.dto.InvoiceResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.gottlieb.sample.domain.Invoice}.
 */
@Service
@Transactional
public class InvoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceService.class);

    @Value("${apis.invoices.activeService}")
    private String activeInvoiceAPI;

    private final ApplicationContext ctx;

    public InvoiceService(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    /**
     * Create Waveapps invoice.
     *
     * @param request InvoiceRequestDTO.
     * @return the created invoice InvoiceResponseDTO.
     */
    public Mono<InvoiceResponseDTO> createInvoice(InvoiceRequestDTO request) {
        LOG.debug("attempting to lookup adapter interface to be used using {}", activeInvoiceAPI);
        InvoiceAdapterInterface adapter = (InvoiceAdapterInterface) ctx.getBean(activeInvoiceAPI);
        LOG.debug("adapter found {}", adapter);
        return adapter.createInvoice(request);
    }
}
