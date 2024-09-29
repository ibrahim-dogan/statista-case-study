package com.statista.taxservice.model;

public class TaxResponse {
    private final double taxRate;

    public TaxResponse(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxRate() {
        return taxRate;
    }
}
