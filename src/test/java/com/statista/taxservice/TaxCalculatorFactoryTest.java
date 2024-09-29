package com.statista.taxservice;

import com.statista.taxservice.domain.DefaultTaxCalculator;
import com.statista.taxservice.domain.EUTaxCalculator;
import com.statista.taxservice.domain.TaxCalculator;
import com.statista.taxservice.domain.TaxCalculatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculatorFactoryTest {

    private TaxCalculatorFactory factory;
    private Map<String, TaxCalculator> calculatorMap;

    @BeforeEach
    void setUp() {
        calculatorMap = new HashMap<>();
        calculatorMap.put("default", new DefaultTaxCalculator());
        calculatorMap.put("eu", new EUTaxCalculator());
        factory = new TaxCalculatorFactory(calculatorMap);
    }

    @Test
    void testGetCalculator_ReturnsCorrectCalculator() {
        TaxCalculator calculator = factory.getCalculator("eu");
        assertEquals(EUTaxCalculator.class, calculator.getClass());

        calculator = factory.getCalculator("unknown");
        assertEquals(DefaultTaxCalculator.class, calculator.getClass());
    }
}
