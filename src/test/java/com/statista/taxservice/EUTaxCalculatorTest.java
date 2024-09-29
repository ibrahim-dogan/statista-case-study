package com.statista.taxservice;

import com.statista.taxservice.domain.EUTaxCalculator;
import com.statista.taxservice.model.TaxRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EUTaxCalculatorTest {

    @Test
    void testCalculateTaxRate() {
        EUTaxCalculator calculator = new EUTaxCalculator();
        TaxRequest request = new TaxRequest();

        double taxRate = calculator.calculateTaxRate(request);

        assertEquals(0.10, taxRate);
    }
}
