package com.gottlieb.sample.service;

import com.gottlieb.sample.repository.QuoteRepository;
import com.gottlieb.sample.service.dto.QuoteDTO;
import com.gottlieb.sample.service.mapper.QuoteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.gottlieb.sample.domain.Quote}.
 */
@Service
@Transactional
public class QuoteService {

    private static final Logger LOG = LoggerFactory.getLogger(QuoteService.class);

    private final QuoteRepository quoteRepository;

    private final QuoteMapper quoteMapper;

    public QuoteService(QuoteRepository quoteRepository, QuoteMapper quoteMapper) {
        this.quoteRepository = quoteRepository;
        this.quoteMapper = quoteMapper;
    }

    /**
     * Save a quote.
     *
     * @param quoteDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuoteDTO> save(QuoteDTO quoteDTO) {
        LOG.debug("Request to save Quote : {}", quoteDTO);
        return quoteRepository.save(quoteMapper.toEntity(quoteDTO)).map(quoteMapper::toDto);
    }

    /**
     * Update a quote.
     *
     * @param quoteDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<QuoteDTO> update(QuoteDTO quoteDTO) {
        LOG.debug("Request to update Quote : {}", quoteDTO);
        return quoteRepository.save(quoteMapper.toEntity(quoteDTO)).map(quoteMapper::toDto);
    }

    /**
     * Partially update a quote.
     *
     * @param quoteDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<QuoteDTO> partialUpdate(QuoteDTO quoteDTO) {
        LOG.debug("Request to partially update Quote : {}", quoteDTO);

        return quoteRepository
            .findById(quoteDTO.getId())
            .map(existingQuote -> {
                quoteMapper.partialUpdate(existingQuote, quoteDTO);

                return existingQuote;
            })
            .flatMap(quoteRepository::save)
            .map(quoteMapper::toDto);
    }

    /**
     * Get all the quotes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<QuoteDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Quotes");
        return quoteRepository.findAllBy(pageable).map(quoteMapper::toDto);
    }

    /**
     * Returns the number of quotes available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return quoteRepository.count();
    }

    /**
     * Get one quote by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<QuoteDTO> findOne(Long id) {
        LOG.debug("Request to get Quote : {}", id);
        return quoteRepository.findById(id).map(quoteMapper::toDto);
    }

    /**
     * Delete the quote by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        LOG.debug("Request to delete Quote : {}", id);
        return quoteRepository.deleteById(id);
    }
}
