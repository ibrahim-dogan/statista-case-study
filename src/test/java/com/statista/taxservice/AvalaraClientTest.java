package com.statista.taxservice;

import com.statista.taxservice.client.AvalaraClient;
import com.statista.taxservice.model.Address;
import com.statista.taxservice.model.AvalaraResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AvalaraClientTest {

    private RestTemplate restTemplate;
    private AvalaraClient avalaraClient;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        avalaraClient = new AvalaraClient(restTemplate, "https://avalara-api.com");
    }

    @Test
    void testGetUSTaxRate_ReturnsTaxRate() {
        Address address = new Address();
        address.setRegion("Utah");

        AvalaraResponse[] response = {new AvalaraResponse(7)};
        when(restTemplate.getForObject(anyString(), eq(AvalaraResponse[].class))).thenReturn(response);

        double taxRate = avalaraClient.getUSTaxRate(address);

        assertEquals(0.07, taxRate);
    }

    @Test
    void testGetUSTaxRate_NoData_ThrowsException() {
        Address address = new Address();
        address.setRegion("Utah");

        when(restTemplate.getForObject(anyString(), eq(AvalaraResponse[].class))).thenReturn(null);

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            avalaraClient.getUSTaxRate(address);
        });

        assertEquals("No tax rate data returned from Avalara", exception.getMessage());
    }
}
