package com.statista.taxservice;

import com.statista.taxservice.controller.TaxController;
import com.statista.taxservice.model.TaxRequest;
import com.statista.taxservice.model.TaxResponse;
import com.statista.taxservice.service.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaxControllerTest {

    private TaxService taxService;
    private TaxController taxController;

    @BeforeEach
    void setUp() {
        taxService = mock(TaxService.class);
        taxController = new TaxController(taxService);
    }

    @Test
    void testGetTaxRate() {
        TaxRequest request = new TaxRequest();
        TaxResponse response = new TaxResponse(0.19);

        when(taxService.getTaxRate(request)).thenReturn(response);

        ResponseEntity<TaxResponse> result = taxController.getTaxRate(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
