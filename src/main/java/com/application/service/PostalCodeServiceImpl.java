package com.application.service;

import com.api.dto.response.PostalCodeResponse;
import com.config.mapper.PostalCodeMapper;
import com.api.exception.PostalCodeNotFoundException;
import com.api.exception.PostalCodeServiceException;
import com.infrastructure.external.PostalCodeApiClient;
import com.infrastructure.persistence.entity.PostalCodeEntity;
import com.infrastructure.persistence.repository.PostalCodeRepository;
import com.application.port.PostalCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostalCodeServiceImpl implements PostalCodeService {

    private final PostalCodeRepository repository;
    private final RestTemplate restTemplate;
    private final PostalCodeMapper mapper;
    private final PostalCodeApiClient client;

    @Value("${zipcode.url}")
    private String zipcodeApiUrl;

    @Override
    public PostalCodeResponse get(String zipCode) {
        if (!isValidZipCode(zipCode)) {
            log.warn("Zipcode not valid: {}", zipCode);
            throw new IllegalArgumentException("Zipcode not valid: " + zipCode);
        }

        try {
            var response = client.fetchPostalCode(zipCode);

            if (response == null) {
                log.error("ZipCode not found: {}", zipCode);
                throw new PostalCodeNotFoundException("ZipCode not found: " + zipCode);
            }

            var entity = repository.save(mapper.postalCodeToPostalCodeEntity(response));
            return mapper.postalCodeEntityToPostalCode(entity);

        } catch (HttpClientErrorException.NotFound e) {
            log.error("Error 404 - ZipCode not found: {}", zipCode);
            throw new PostalCodeNotFoundException("ZipCode not found: " + zipCode);
        } catch (RestClientException e) {
            log.error("Error occurred while fetching data for zipCode {}: {}", zipCode, e.getMessage());
            throw new PostalCodeServiceException("Failed to fetch PostalCode", e);
        }
    }


    @Override
    public List<PostalCodeResponse> getLogs() {

        List<PostalCodeEntity> response = StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return response.stream()
                .map(mapper::postalCodeEntityToPostalCode)
                .collect(Collectors.toList());
    }

    private boolean isValidZipCode(String zipCode) {
        if (zipCode == null) {
            return false;
        }

        return zipCode.matches("\\d{8}");
    }
}


