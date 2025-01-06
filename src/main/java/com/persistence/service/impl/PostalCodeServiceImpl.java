package com.persistence.service.impl;

import com.api.dto.PostalCode;
import com.config.mapper.PostalCodeMapper;
import com.exception.PostalCodeNotFoundException;
import com.exception.PostalCodeServiceException;
import com.persistence.entity.PostalCodeEntity;
import com.persistence.repository.PostalCodeRepository;
import com.persistence.service.PostalCodeService;
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

    @Value("${zipcode.url}")
    private String zipcodeApiUrl;

    @Override
    public PostalCode get(String zipCode) {
        if (!isValidZipCode(zipCode)) {
            log.warn("Zipcode not valid: {}", zipCode);
            throw new IllegalArgumentException("Zipcode not valid: " + zipCode);
        }

        try {
            // Faz a chamada para a API WireMock
            var response = restTemplate.getForObject(zipcodeApiUrl + "/" + zipCode, PostalCode.class);

            if (response == null) {
                log.error("ZipCode not found: {}", zipCode);
                throw new PostalCodeNotFoundException("ZipCode not found: " + zipCode);
            }

            var entity = repository.save(mapper.postalCodeToPostalCodeEntity(response));
            return mapper.postalCodeEntityToPostalCode(entity);

        } catch (HttpClientErrorException.NotFound e) {
            // Aqui tratamos o erro 404 especificamente e retornamos um erro adequado
            log.error("Error 404 - ZipCode not found: {}", zipCode);
            throw new PostalCodeNotFoundException("ZipCode not found: " + zipCode);
        } catch (RestClientException e) {
            // Aqui você pode tratar outros tipos de erros HTTP
            log.error("Error occurred while fetching data for zipCode {}: {}", zipCode, e.getMessage());
            throw new PostalCodeServiceException("Failed to fetch PostalCode", e);
        }
    }


    @Override
    public List<PostalCode> getLogs() {

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


