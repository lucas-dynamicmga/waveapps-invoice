package com.gottlieb.sample.service.dto;

import java.util.List;

public class InvoiceRequestDTO {       
    private List<InvoiceLineItemDTO> items;
    private String referenceNumber;
    private AccountDTO account;

    public List<InvoiceLineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<InvoiceLineItemDTO> items) {
        this.items = items;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "InvoiceRequest{" +
                "items=" + items +             
                '}';
    }

}
