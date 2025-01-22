package com.gottlieb.sample.domain.enumeration;

/**
 * The QuoteStatus enumeration.
 */
public enum QuoteStatus {
    CREATED("Created"),
    IN_PROCESS("In Process"),
    READY("Ready"),
    BOUND("Bound");

    private final String value;

    QuoteStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
