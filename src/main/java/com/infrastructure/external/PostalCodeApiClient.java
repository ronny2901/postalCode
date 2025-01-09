package com.infrastructure.external;

import com.api.dto.response.PostalCodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostalCodeApiClient {

    private final RestTemplate restTemplate;

    @Value("${zipcode.url}")
    private String zipcodeApiUrl;

    public PostalCodeApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PostalCodeResponse fetchPostalCode(String zipCode) {
        return restTemplate.getForObject(zipcodeApiUrl + "/" + zipCode, PostalCodeResponse.class);
    }
}

