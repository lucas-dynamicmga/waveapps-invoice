package com.gottlieb.sample.repository;

import com.gottlieb.sample.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the Quote entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuoteRepository extends ReactiveCrudRepository<Quote, Long>, QuoteRepositoryInternal {
    Flux<Quote> findAllBy(Pageable pageable);

    @Override
    <S extends Quote> Mono<S> save(S entity);

    @Override
    Flux<Quote> findAll();

    @Override
    Mono<Quote> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface QuoteRepositoryInternal {
    <S extends Quote> Mono<S> save(S entity);

    Flux<Quote> findAllBy(Pageable pageable);

    Flux<Quote> findAll();

    Mono<Quote> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Quote> findAllBy(Pageable pageable, Criteria criteria);
}
