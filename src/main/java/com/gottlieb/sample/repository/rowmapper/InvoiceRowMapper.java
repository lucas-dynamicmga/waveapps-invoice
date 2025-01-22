package com.gottlieb.sample.repository.rowmapper;

import com.gottlieb.sample.domain.Invoice;
import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Invoice}, with proper type conversions.
 */
@Service
public class InvoiceRowMapper implements BiFunction<Row, String, Invoice> {

    private final ColumnConverter converter;

    public InvoiceRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Invoice} stored in the database.
     */
    @Override
    public Invoice apply(Row row, String prefix) {
        Invoice entity = new Invoice();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setInvoiceId(converter.fromRow(row, prefix + "_invoice_id", Long.class));
        entity.setAgencyId(converter.fromRow(row, prefix + "_agency_id", Long.class));
        entity.setStatusId(converter.fromRow(row, prefix + "_status_id", Long.class));
        entity.setTitle(converter.fromRow(row, prefix + "_title", String.class));
        entity.setSubhead(converter.fromRow(row, prefix + "_subhead", String.class));
        entity.setInvoiceNumber(converter.fromRow(row, prefix + "_invoice_number", String.class));
        entity.setPoNumber(converter.fromRow(row, prefix + "_po_number", String.class));
        entity.setDueData(converter.fromRow(row, prefix + "_due_data", Instant.class));
        entity.setPdfUrl(converter.fromRow(row, prefix + "_pdf_url", String.class));
        return entity;
    }
}
