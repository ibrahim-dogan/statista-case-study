package com.statista.taxservice;

import com.statista.taxservice.domain.GermanyTaxCalculator;
import com.statista.taxservice.model.TaxRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GermanyTaxCalculatorTest {

    @Test
    void testCalculateTaxRate_Ebook() {
        GermanyTaxCalculator calculator = new GermanyTaxCalculator();
        TaxRequest request = new TaxRequest();
        request.setProductCategory("ebook");

        double taxRate = calculator.calculateTaxRate(request);

        assertEquals(0.07, taxRate);
    }

    @Test
    void testCalculateTaxRate_Subscription() {
        GermanyTaxCalculator calculator = new GermanyTaxCalculator();
        TaxRequest request = new TaxRequest();
        request.setProductCategory("subscription");

        double taxRate = calculator.calculateTaxRate(request);

        assertEquals(0.19, taxRate);
    }

    @Test
    void testCalculateTaxRate_Default() {
        GermanyTaxCalculator calculator = new GermanyTaxCalculator();
        TaxRequest request = new TaxRequest();
        request.setProductCategory("blabla");

        double taxRate = calculator.calculateTaxRate(request);

        assertEquals(0.19, taxRate);
    }
}
