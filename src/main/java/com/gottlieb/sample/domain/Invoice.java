package com.gottlieb.sample.domain;

import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Invoice.
 */
@Table("invoice")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("invoice_id")
    private Long invoiceId;

    @Column("agency_id")
    private Long agencyId;

    @Column("status_id")
    private Long statusId;

    @Column("title")
    private String title;

    @Column("subhead")
    private String subhead;

    @Column("invoice_number")
    private String invoiceNumber;

    @Column("po_number")
    private String poNumber;

    @Column("due_data")
    private Instant dueData;

    @Column("pdf_url")
    private String pdfUrl;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Invoice id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInvoiceId() {
        return this.invoiceId;
    }

    public Invoice invoiceId(Long invoiceId) {
        this.setInvoiceId(invoiceId);
        return this;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getAgencyId() {
        return this.agencyId;
    }

    public Invoice agencyId(Long agencyId) {
        this.setAgencyId(agencyId);
        return this;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getStatusId() {
        return this.statusId;
    }

    public Invoice statusId(Long statusId) {
        this.setStatusId(statusId);
        return this;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getTitle() {
        return this.title;
    }

    public Invoice title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubhead() {
        return this.subhead;
    }

    public Invoice subhead(String subhead) {
        this.setSubhead(subhead);
        return this;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public Invoice invoiceNumber(String invoiceNumber) {
        this.setInvoiceNumber(invoiceNumber);
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getPoNumber() {
        return this.poNumber;
    }

    public Invoice poNumber(String poNumber) {
        this.setPoNumber(poNumber);
        return this;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Instant getDueData() {
        return this.dueData;
    }

    public Invoice dueData(Instant dueData) {
        this.setDueData(dueData);
        return this;
    }

    public void setDueData(Instant dueData) {
        this.dueData = dueData;
    }

    public String getPdfUrl() {
        return this.pdfUrl;
    }

    public Invoice pdfUrl(String pdfUrl) {
        this.setPdfUrl(pdfUrl);
        return this;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Invoice)) {
            return false;
        }
        return getId() != null && getId().equals(((Invoice) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Invoice{" +
            "id=" + getId() +
            ", invoiceId=" + getInvoiceId() +
            ", agencyId=" + getAgencyId() +
            ", statusId=" + getStatusId() +
            ", title='" + getTitle() + "'" +
            ", subhead='" + getSubhead() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", poNumber='" + getPoNumber() + "'" +
            ", dueData='" + getDueData() + "'" +
            ", pdfUrl='" + getPdfUrl() + "'" +
            "}";
    }
}
