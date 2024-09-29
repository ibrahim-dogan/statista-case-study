package com.statista.taxservice;

import com.statista.taxservice.domain.TaxCalculator;
import com.statista.taxservice.domain.TaxCalculatorFactory;
import com.statista.taxservice.model.Address;
import com.statista.taxservice.model.TaxRequest;
import com.statista.taxservice.model.TaxResponse;
import com.statista.taxservice.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxServiceTest {

    private TaxCalculatorFactory taxCalculatorFactory;
    private TaxService taxService;

    @BeforeEach
    void setUp() {
        taxCalculatorFactory = Mockito.mock(TaxCalculatorFactory.class);
        taxService = new TaxService(taxCalculatorFactory);
    }

    @Test
    void testGetTaxRate() {
        TaxRequest request = new TaxRequest();
        request.setProductCategory("ebook");

        Address address = new Address();
        address.setCountry("de");
        request.setAddress(address);

        TaxCalculator calculator = Mockito.mock(TaxCalculator.class);
        Mockito.when(taxCalculatorFactory.getCalculator("de")).thenReturn(calculator);
        Mockito.when(calculator.calculateTaxRate(request)).thenReturn(0.07);

        TaxResponse response = taxService.getTaxRate(request);
        assertEquals(0.07, response.getTaxRate());
    }
}
