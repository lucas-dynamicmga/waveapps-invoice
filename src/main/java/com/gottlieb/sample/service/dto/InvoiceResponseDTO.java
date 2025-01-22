package com.gottlieb.sample.service.dto;

public class InvoiceResponseDTO {
    private String invoiceId;
    private String invoiceStatus;
    private AccountDTO account;
    private String documentUrl;


    public String getInvoiceId() {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceStatus() {
        return this.invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public AccountDTO getAccount() {
        return this.account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public String getDocumentUrl() {
        return this.documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
    
}