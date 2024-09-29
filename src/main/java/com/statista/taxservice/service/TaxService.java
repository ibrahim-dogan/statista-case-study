package com.statista.taxservice.service;

import com.statista.taxservice.domain.TaxCalculator;
import com.statista.taxservice.domain.TaxCalculatorFactory;
import com.statista.taxservice.model.TaxRequest;
import com.statista.taxservice.model.TaxResponse;
import org.springframework.stereotype.Service;

@Service
public class TaxService {

    private final TaxCalculatorFactory taxCalculatorFactory;

    public TaxService(TaxCalculatorFactory taxCalculatorFactory) {
        this.taxCalculatorFactory = taxCalculatorFactory;
    }

    public TaxResponse getTaxRate(TaxRequest request) {
        TaxCalculator calculator = taxCalculatorFactory.getCalculator(request.getAddress().getCountry());
        double taxRate = calculator.calculateTaxRate(request);
        return new TaxResponse(taxRate);
    }
}
