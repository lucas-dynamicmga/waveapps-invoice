package com.gottlieb.sample.domain;

import static com.gottlieb.sample.domain.QuoteTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.gottlieb.sample.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QuoteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Quote.class);
        Quote quote1 = getQuoteSample1();
        Quote quote2 = new Quote();
        assertThat(quote1).isNotEqualTo(quote2);

        quote2.setId(quote1.getId());
        assertThat(quote1).isEqualTo(quote2);

        quote2 = getQuoteSample2();
        assertThat(quote1).isNotEqualTo(quote2);
    }
}
