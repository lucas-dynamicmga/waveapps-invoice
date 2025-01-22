package com.gottlieb.sample.web.rest;

import com.gottlieb.sample.repository.QuoteRepository;
import com.gottlieb.sample.service.QuoteService;
import com.gottlieb.sample.service.dto.QuoteDTO;
import com.gottlieb.sample.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.ForwardedHeaderUtils;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.gottlieb.sample.domain.Quote}.
 */
@RestController
@RequestMapping("/api/quotes")
public class QuoteResource {

    private static final Logger LOG = LoggerFactory.getLogger(QuoteResource.class);

    private static final String ENTITY_NAME = "quote";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuoteService quoteService;

    private final QuoteRepository quoteRepository;

    public QuoteResource(QuoteService quoteService, QuoteRepository quoteRepository) {
        this.quoteService = quoteService;
        this.quoteRepository = quoteRepository;
    }

    /**
     * {@code POST  /quotes} : Create a new quote.
     *
     * @param quoteDTO the quoteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quoteDTO, or with status {@code 400 (Bad Request)} if the quote has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<QuoteDTO>> createQuote(@RequestBody QuoteDTO quoteDTO) throws URISyntaxException {
        LOG.debug("REST request to save Quote : {}", quoteDTO);
        if (quoteDTO.getId() != null) {
            throw new BadRequestAlertException("A new quote cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return quoteService
            .save(quoteDTO)
            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/quotes/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /quotes/:id} : Updates an existing quote.
     *
     * @param id the id of the quoteDTO to save.
     * @param quoteDTO the quoteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quoteDTO,
     * or with status {@code 400 (Bad Request)} if the quoteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quoteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<QuoteDTO>> updateQuote(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuoteDTO quoteDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Quote : {}, {}", id, quoteDTO);
        if (quoteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, quoteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return quoteRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return quoteService
                    .update(quoteDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity.ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /quotes/:id} : Partial updates given fields of an existing quote, field will ignore if it is null
     *
     * @param id the id of the quoteDTO to save.
     * @param quoteDTO the quoteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quoteDTO,
     * or with status {@code 400 (Bad Request)} if the quoteDTO is not valid,
     * or with status {@code 404 (Not Found)} if the quoteDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the quoteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<QuoteDTO>> partialUpdateQuote(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody QuoteDTO quoteDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Quote partially : {}, {}", id, quoteDTO);
        if (quoteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, quoteDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return quoteRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<QuoteDTO> result = quoteService.partialUpdate(quoteDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity.ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /quotes} : get all the quotes.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quotes in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<QuoteDTO>>> getAllQuotes(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        LOG.debug("REST request to get a page of Quotes");
        return quoteService
            .countAll()
            .zipWith(quoteService.findAll(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity.ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /quotes/:id} : get the "id" quote.
     *
     * @param id the id of the quoteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quoteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<QuoteDTO>> getQuote(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Quote : {}", id);
        Mono<QuoteDTO> quoteDTO = quoteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quoteDTO);
    }

    /**
     * {@code DELETE  /quotes/:id} : delete the "id" quote.
     *
     * @param id the id of the quoteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteQuote(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Quote : {}", id);
        return quoteService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity.noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
