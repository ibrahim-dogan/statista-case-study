package com.statista.taxservice.domain;

import com.statista.taxservice.model.TaxRequest;
import org.springframework.stereotype.Component;

@Component("eu")
public class EUTaxCalculator implements TaxCalculator {

    @Override
    public double calculateTaxRate(TaxRequest request) {
        return 0.10; // Fixed 10% for EU countries
    }
}
