package com.statista.taxservice.client;

import com.statista.taxservice.model.Address;
import com.statista.taxservice.model.AvalaraResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AvalaraClient {

    private final RestTemplate restTemplate;
    private final String avalaraUrl;

    // Inject the externalized URL using @Value
    public AvalaraClient(RestTemplate restTemplate, @Value("${avalara.api.url}") String avalaraUrl) {
        this.restTemplate = restTemplate;
        this.avalaraUrl = avalaraUrl;
    }

    public double getUSTaxRate(Address address) {
        String url = avalaraUrl + address.getRegion();
        AvalaraResponse[] responseArray = restTemplate.getForObject(url, AvalaraResponse[].class);

        if (responseArray != null && responseArray.length > 0) {
            return responseArray[0].getRate() / 100;
        }

        throw new RuntimeException("No tax rate data returned from Avalara");
    }
}
