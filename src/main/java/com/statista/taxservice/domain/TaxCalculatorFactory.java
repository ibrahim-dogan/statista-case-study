package com.statista.taxservice.domain;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaxCalculatorFactory {

    private final Map<String, TaxCalculator> calculatorMap;

    public TaxCalculatorFactory(Map<String, TaxCalculator> calculatorMap) {
        this.calculatorMap = calculatorMap;
    }

    public TaxCalculator getCalculator(String country) {
        return calculatorMap.getOrDefault(country.toLowerCase(), calculatorMap.get("default"));
    }
}
