package com.statista.taxservice.domain;

import com.statista.taxservice.model.TaxRequest;

public interface TaxCalculator {
    double calculateTaxRate(TaxRequest request);
}
