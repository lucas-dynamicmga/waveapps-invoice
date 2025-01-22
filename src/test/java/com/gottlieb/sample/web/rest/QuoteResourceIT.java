package com.gottlieb.sample.web.rest;

import static com.gottlieb.sample.domain.QuoteAsserts.*;
import static com.gottlieb.sample.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gottlieb.sample.IntegrationTest;
import com.gottlieb.sample.domain.Quote;
import com.gottlieb.sample.domain.enumeration.QuoteStatus;
import com.gottlieb.sample.repository.EntityManager;
import com.gottlieb.sample.repository.QuoteRepository;
import com.gottlieb.sample.service.dto.QuoteDTO;
import com.gottlieb.sample.service.mapper.QuoteMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link QuoteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class QuoteResourceIT {

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_LAST_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final QuoteStatus DEFAULT_STATUS = QuoteStatus.CREATED;
    private static final QuoteStatus UPDATED_STATUS = QuoteStatus.IN_PROCESS;

    private static final String ENTITY_API_URL = "/api/quotes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private QuoteMapper quoteMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Quote quote;

    private Quote insertedQuote;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quote createEntity() {
        return new Quote()
            .identifier(DEFAULT_IDENTIFIER)
            .description(DEFAULT_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .createdDate(DEFAULT_CREATED_DATE)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .status(DEFAULT_STATUS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Quote createUpdatedEntity() {
        return new Quote()
            .identifier(UPDATED_IDENTIFIER)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .status(UPDATED_STATUS);
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Quote.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @BeforeEach
    public void setupCsrf() {
        webTestClient = webTestClient.mutateWith(csrf());
    }

    @BeforeEach
    public void initTest() {
        quote = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedQuote != null) {
            quoteRepository.delete(insertedQuote).block();
            insertedQuote = null;
        }
        deleteEntities(em);
    }

    @Test
    void createQuote() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);
        var returnedQuoteDTO = webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(QuoteDTO.class)
            .returnResult()
            .getResponseBody();

        // Validate the Quote in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedQuote = quoteMapper.toEntity(returnedQuoteDTO);
        assertQuoteUpdatableFieldsEquals(returnedQuote, getPersistedQuote(returnedQuote));

        insertedQuote = returnedQuote;
    }

    @Test
    void createQuoteWithExistingId() throws Exception {
        // Create the Quote with an existing ID
        quote.setId(1L);
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void getAllQuotes() {
        // Initialize the database
        insertedQuote = quoteRepository.save(quote).block();

        // Get all the quoteList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(quote.getId().intValue()))
            .jsonPath("$.[*].identifier")
            .value(hasItem(DEFAULT_IDENTIFIER))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].createdDate")
            .value(hasItem(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.[*].lastModifiedBy")
            .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
            .jsonPath("$.[*].lastModifiedDate")
            .value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()));
    }

    @Test
    void getQuote() {
        // Initialize the database
        insertedQuote = quoteRepository.save(quote).block();

        // Get the quote
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, quote.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(quote.getId().intValue()))
            .jsonPath("$.identifier")
            .value(is(DEFAULT_IDENTIFIER))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.createdDate")
            .value(is(DEFAULT_CREATED_DATE.toString()))
            .jsonPath("$.lastModifiedBy")
            .value(is(DEFAULT_LAST_MODIFIED_BY))
            .jsonPath("$.lastModifiedDate")
            .value(is(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()));
    }

    @Test
    void getNonExistingQuote() {
        // Get the quote
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingQuote() throws Exception {
        // Initialize the database
        insertedQuote = quoteRepository.save(quote).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quote
        Quote updatedQuote = quoteRepository.findById(quote.getId()).block();
        updatedQuote
            .identifier(UPDATED_IDENTIFIER)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .status(UPDATED_STATUS);
        QuoteDTO quoteDTO = quoteMapper.toDto(updatedQuote);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, quoteDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQuoteToMatchAllProperties(updatedQuote);
    }

    @Test
    void putNonExistingQuote() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quote.setId(longCount.incrementAndGet());

        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, quoteDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchQuote() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quote.setId(longCount.incrementAndGet());

        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamQuote() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quote.setId(longCount.incrementAndGet());

        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateQuoteWithPatch() throws Exception {
        // Initialize the database
        insertedQuote = quoteRepository.save(quote).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quote using partial update
        Quote partialUpdatedQuote = new Quote();
        partialUpdatedQuote.setId(quote.getId());

        partialUpdatedQuote
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedQuote.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedQuote))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Quote in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuoteUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedQuote, quote), getPersistedQuote(quote));
    }

    @Test
    void fullUpdateQuoteWithPatch() throws Exception {
        // Initialize the database
        insertedQuote = quoteRepository.save(quote).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the quote using partial update
        Quote partialUpdatedQuote = new Quote();
        partialUpdatedQuote.setId(quote.getId());

        partialUpdatedQuote
            .identifier(UPDATED_IDENTIFIER)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .createdDate(UPDATED_CREATED_DATE)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedQuote.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedQuote))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Quote in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQuoteUpdatableFieldsEquals(partialUpdatedQuote, getPersistedQuote(partialUpdatedQuote));
    }

    @Test
    void patchNonExistingQuote() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quote.setId(longCount.incrementAndGet());

        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, quoteDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchQuote() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quote.setId(longCount.incrementAndGet());

        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamQuote() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        quote.setId(longCount.incrementAndGet());

        // Create the Quote
        QuoteDTO quoteDTO = quoteMapper.toDto(quote);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(quoteDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Quote in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteQuote() {
        // Initialize the database
        insertedQuote = quoteRepository.save(quote).block();

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the quote
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, quote.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return quoteRepository.count().block();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Quote getPersistedQuote(Quote quote) {
        return quoteRepository.findById(quote.getId()).block();
    }

    protected void assertPersistedQuoteToMatchAllProperties(Quote expectedQuote) {
        // Test fails because reactive api returns an empty object instead of null
        // assertQuoteAllPropertiesEquals(expectedQuote, getPersistedQuote(expectedQuote));
        assertQuoteUpdatableFieldsEquals(expectedQuote, getPersistedQuote(expectedQuote));
    }

    protected void assertPersistedQuoteToMatchUpdatableProperties(Quote expectedQuote) {
        // Test fails because reactive api returns an empty object instead of null
        // assertQuoteAllUpdatablePropertiesEquals(expectedQuote, getPersistedQuote(expectedQuote));
        assertQuoteUpdatableFieldsEquals(expectedQuote, getPersistedQuote(expectedQuote));
    }
}
