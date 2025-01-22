package com.gottlieb.sample.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InvoiceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Invoice getInvoiceSample1() {
        return new Invoice()
            .id(1L)
            .invoiceId(1L)
            .agencyId(1L)
            .statusId(1L)
            .title("title1")
            .subhead("subhead1")
            .invoiceNumber("invoiceNumber1")
            .poNumber("poNumber1")
            .pdfUrl("pdfUrl1");
    }

    public static Invoice getInvoiceSample2() {
        return new Invoice()
            .id(2L)
            .invoiceId(2L)
            .agencyId(2L)
            .statusId(2L)
            .title("title2")
            .subhead("subhead2")
            .invoiceNumber("invoiceNumber2")
            .poNumber("poNumber2")
            .pdfUrl("pdfUrl2");
    }

    public static Invoice getInvoiceRandomSampleGenerator() {
        return new Invoice()
            .id(longCount.incrementAndGet())
            .invoiceId(longCount.incrementAndGet())
            .agencyId(longCount.incrementAndGet())
            .statusId(longCount.incrementAndGet())
            .title(UUID.randomUUID().toString())
            .subhead(UUID.randomUUID().toString())
            .invoiceNumber(UUID.randomUUID().toString())
            .poNumber(UUID.randomUUID().toString())
            .pdfUrl(UUID.randomUUID().toString());
    }
}
