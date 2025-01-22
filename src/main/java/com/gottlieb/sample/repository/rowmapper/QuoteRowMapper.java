package com.gottlieb.sample.repository.rowmapper;

import com.gottlieb.sample.domain.Quote;
import com.gottlieb.sample.domain.enumeration.QuoteStatus;
import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Quote}, with proper type conversions.
 */
@Service
public class QuoteRowMapper implements BiFunction<Row, String, Quote> {

    private final ColumnConverter converter;

    public QuoteRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Quote} stored in the database.
     */
    @Override
    public Quote apply(Row row, String prefix) {
        Quote entity = new Quote();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setIdentifier(converter.fromRow(row, prefix + "_identifier", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setCreatedDate(converter.fromRow(row, prefix + "_created_date", Instant.class));
        entity.setLastModifiedBy(converter.fromRow(row, prefix + "_last_modified_by", String.class));
        entity.setLastModifiedDate(converter.fromRow(row, prefix + "_last_modified_date", Instant.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", QuoteStatus.class));
        return entity;
    }
}
