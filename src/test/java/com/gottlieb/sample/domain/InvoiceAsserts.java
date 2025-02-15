package com.gottlieb.sample.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class InvoiceAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInvoiceAllPropertiesEquals(Invoice expected, Invoice actual) {
        assertInvoiceAutoGeneratedPropertiesEquals(expected, actual);
        assertInvoiceAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInvoiceAllUpdatablePropertiesEquals(Invoice expected, Invoice actual) {
        assertInvoiceUpdatableFieldsEquals(expected, actual);
        assertInvoiceUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInvoiceAutoGeneratedPropertiesEquals(Invoice expected, Invoice actual) {
        assertThat(expected)
            .as("Verify Invoice auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInvoiceUpdatableFieldsEquals(Invoice expected, Invoice actual) {
        assertThat(expected)
            .as("Verify Invoice relevant properties")
            .satisfies(e -> assertThat(e.getInvoiceId()).as("check invoiceId").isEqualTo(actual.getInvoiceId()))
            .satisfies(e -> assertThat(e.getAgencyId()).as("check agencyId").isEqualTo(actual.getAgencyId()))
            .satisfies(e -> assertThat(e.getStatusId()).as("check statusId").isEqualTo(actual.getStatusId()))
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()))
            .satisfies(e -> assertThat(e.getSubhead()).as("check subhead").isEqualTo(actual.getSubhead()))
            .satisfies(e -> assertThat(e.getInvoiceNumber()).as("check invoiceNumber").isEqualTo(actual.getInvoiceNumber()))
            .satisfies(e -> assertThat(e.getPoNumber()).as("check poNumber").isEqualTo(actual.getPoNumber()))
            .satisfies(e -> assertThat(e.getDueData()).as("check dueData").isEqualTo(actual.getDueData()))
            .satisfies(e -> assertThat(e.getPdfUrl()).as("check pdfUrl").isEqualTo(actual.getPdfUrl()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertInvoiceUpdatableRelationshipsEquals(Invoice expected, Invoice actual) {
        // empty method
    }
}
