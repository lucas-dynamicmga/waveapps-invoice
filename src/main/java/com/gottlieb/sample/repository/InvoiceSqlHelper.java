package com.gottlieb.sample.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class InvoiceSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("invoice_id", table, columnPrefix + "_invoice_id"));
        columns.add(Column.aliased("agency_id", table, columnPrefix + "_agency_id"));
        columns.add(Column.aliased("status_id", table, columnPrefix + "_status_id"));
        columns.add(Column.aliased("title", table, columnPrefix + "_title"));
        columns.add(Column.aliased("subhead", table, columnPrefix + "_subhead"));
        columns.add(Column.aliased("invoice_number", table, columnPrefix + "_invoice_number"));
        columns.add(Column.aliased("po_number", table, columnPrefix + "_po_number"));
        columns.add(Column.aliased("due_data", table, columnPrefix + "_due_data"));
        columns.add(Column.aliased("pdf_url", table, columnPrefix + "_pdf_url"));

        return columns;
    }
}
