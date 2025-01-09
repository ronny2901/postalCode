package com.api.controller;


import com.api.dto.response.PostalCodeResponse;
import com.application.port.PostalCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(PostalCodeController.RESOURCE_PATH_POSTAL_CODE)
public class PostalCodeController {

    public static final String RESOURCE_PATH_POSTAL_CODE = "/postalcode";

    private final PostalCodeService service;

    @GetMapping(path = "/{zipCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostalCodeResponse> getPostalCode(@PathVariable final String zipCode) {

        return ResponseEntity.ok().body(service.get(zipCode));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostalCodeResponse>> getAllPostalCodeLogs() {

        return ResponseEntity.ok().body(service.getLogs());
    }
}
