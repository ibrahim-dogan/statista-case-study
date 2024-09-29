package com.statista.taxservice.domain;

import com.statista.taxservice.model.TaxRequest;
import org.springframework.stereotype.Component;

@Component("germany")
public class GermanyTaxCalculator implements TaxCalculator {

    @Override
    public double calculateTaxRate(TaxRequest request) {
        String productCategory = request.getProductCategory().toLowerCase();
        if (productCategory.equals("ebook")) {
            return 0.07;
        } else if (productCategory.equals("subscription")) {
            return 0.19;
        }
        return 0.19;  // Default tax rate
    }
}
