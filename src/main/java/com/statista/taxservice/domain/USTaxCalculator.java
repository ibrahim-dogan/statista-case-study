package com.statista.taxservice.domain;

import com.statista.taxservice.client.AvalaraClient;
import com.statista.taxservice.model.TaxRequest;
import org.springframework.stereotype.Component;

@Component("us")
public class USTaxCalculator implements TaxCalculator {

    private final AvalaraClient client;

    public USTaxCalculator(AvalaraClient client) {
        this.client = client;
    }

    @Override
    public double calculateTaxRate(TaxRequest request) {
        return client.getUSTaxRate(request.getAddress());
    }
}
