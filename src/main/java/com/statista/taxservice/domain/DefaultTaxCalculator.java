package com.statista.taxservice.domain;

import com.statista.taxservice.model.TaxRequest;
import org.springframework.stereotype.Component;

@Component("default")
public class DefaultTaxCalculator implements TaxCalculator {

    @Override
    public double calculateTaxRate(TaxRequest request) {
        return 0.0; // No tax for outside EU and US
    }
}
