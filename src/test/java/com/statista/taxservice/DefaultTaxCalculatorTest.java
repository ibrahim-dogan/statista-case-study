package com.statista.taxservice;

import com.statista.taxservice.domain.DefaultTaxCalculator;
import com.statista.taxservice.model.TaxRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultTaxCalculatorTest {

    @Test
    void testCalculateTaxRate() {
        DefaultTaxCalculator calculator = new DefaultTaxCalculator();
        TaxRequest request = new TaxRequest();

        double taxRate = calculator.calculateTaxRate(request);

        assertEquals(0.0, taxRate);
    }
}
