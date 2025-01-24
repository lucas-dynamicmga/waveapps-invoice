package com.gottlieb.sample.service.adapter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WaveappsCreateInvoiceResponse {

    private Data data;
    private List<Error> errors;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        @JsonProperty("invoiceCreate")
        private InvoiceCreate invoiceCreate;

        public InvoiceCreate getInvoiceCreate() {
            return invoiceCreate;
        }

        public void setInvoiceCreate(InvoiceCreate invoiceCreate) {
            this.invoiceCreate = invoiceCreate;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class InvoiceCreate {

        private boolean didSucceed;
        private Object inputErrors;
        private Invoice invoice;

        public boolean isDidSucceed() {
            return didSucceed;
        }

        public void setDidSucceed(boolean didSucceed) {
            this.didSucceed = didSucceed;
        }

        public Object getInputErrors() {
            return inputErrors;
        }

        public void setInputErrors(Object inputErrors) {
            this.inputErrors = inputErrors;
        }

        public Invoice getInvoice() {
            return invoice;
        }

        public void setInvoice(Invoice invoice) {
            this.invoice = invoice;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Invoice {

        private String id;
        private String pdfUrl;
        private String status;
        private Total total;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPdfUrl() {
            return pdfUrl;
        }

        public void setPdfUrl(String pdfUrl) {
            this.pdfUrl = pdfUrl;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Total getTotal() {
            return total;
        }

        public void setTotal(Total total) {
            this.total = total;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Total {

        private String value;
        private Currency currency;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Currency {

        private String symbol;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Error {

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
