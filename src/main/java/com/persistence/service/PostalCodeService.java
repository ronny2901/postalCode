package com.persistence.service;

import com.api.dto.PostalCode;

import java.util.List;

public interface PostalCodeService {

    PostalCode get(String zipCode);

    List<PostalCode> getLogs();
}
