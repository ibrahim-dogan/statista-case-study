package com.statista.taxservice;

import com.statista.taxservice.client.AvalaraClient;
import com.statista.taxservice.domain.USTaxCalculator;
import com.statista.taxservice.model.Address;
import com.statista.taxservice.model.TaxRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class USTaxCalculatorTest {

    private AvalaraClient avalaraClient;
    private USTaxCalculator usTaxCalculator;

    @BeforeEach
    void setUp() {
        avalaraClient = Mockito.mock(AvalaraClient.class);
        usTaxCalculator = new USTaxCalculator(avalaraClient);
    }

    @Test
    void testCalculateTaxRate() {
        Address address = new Address();
        address.setCountry("US");
        address.setRegion("California");

        TaxRequest request = new TaxRequest();
        request.setAddress(address);

        double expectedTaxRate = 0.07;
        Mockito.when(avalaraClient.getUSTaxRate(address)).thenReturn(expectedTaxRate);

        double actualTaxRate = usTaxCalculator.calculateTaxRate(request);

        assertEquals(expectedTaxRate, actualTaxRate);
    }
}
