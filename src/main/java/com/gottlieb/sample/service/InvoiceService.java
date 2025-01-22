package com.gottlieb.sample.service;

import com.gottlieb.sample.repository.InvoiceRepository;
import com.gottlieb.sample.service.adapter.InvoiceAdapter;
import com.gottlieb.sample.service.dto.InvoiceDTO;
import com.gottlieb.sample.service.dto.InvoiceRequestDTO;
import com.gottlieb.sample.service.dto.InvoiceResponseDTO;
import com.gottlieb.sample.service.mapper.InvoiceMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.gottlieb.sample.domain.Invoice}.
 */
@Service
@Transactional
public class InvoiceService {

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceService.class);

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    @Autowired
    private InvoiceAdapter invoiceAdapter;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    /**
     * Save a invoice.
     *
     * @param invoiceDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<InvoiceDTO> save(InvoiceDTO invoiceDTO) {
        LOG.debug("Request to save Invoice : {}", invoiceDTO);
        return invoiceRepository.save(invoiceMapper.toEntity(invoiceDTO)).map(invoiceMapper::toDto);
    }

    /**
     * Update a invoice.
     *
     * @param invoiceDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<InvoiceDTO> update(InvoiceDTO invoiceDTO) {
        LOG.debug("Request to update Invoice : {}", invoiceDTO);
        return invoiceRepository.save(invoiceMapper.toEntity(invoiceDTO)).map(invoiceMapper::toDto);
    }

    /**
     * Partially update a invoice.
     *
     * @param invoiceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<InvoiceDTO> partialUpdate(InvoiceDTO invoiceDTO) {
        LOG.debug("Request to partially update Invoice : {}", invoiceDTO);

        return invoiceRepository
            .findById(invoiceDTO.getId())
            .map(existingInvoice -> {
                invoiceMapper.partialUpdate(existingInvoice, invoiceDTO);

                return existingInvoice;
            })
            .flatMap(invoiceRepository::save)
            .map(invoiceMapper::toDto);
    }

    /**
     * Get all the invoices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<InvoiceDTO> findAll() {
        LOG.debug("Request to get all Invoices");
        return invoiceRepository.findAll().map(invoiceMapper::toDto);
    }

    /**
     * Returns the number of invoices available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return invoiceRepository.count();
    }

    /**
     * Get one invoice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<InvoiceDTO> findOne(Long id) {
        LOG.debug("Request to get Invoice : {}", id);
        return invoiceRepository.findById(id).map(invoiceMapper::toDto);
    }

    /**
     * Delete the invoice by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        LOG.debug("Request to delete Invoice : {}", id);
        return invoiceRepository.deleteById(id);
    }  

    /**
     * Create Waveapps invoice.
     *
     * @param request the request to create invoice.
     * @return the created invoice.
     */
    public Mono<InvoiceResponseDTO> createInvoice(InvoiceRequestDTO request) {
        return invoiceAdapter.createInvoice(request);
    }
    
}
