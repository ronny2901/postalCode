package com.application.port;

import com.api.dto.response.PostalCodeResponse;

import java.util.List;

public interface PostalCodeService {

    PostalCodeResponse get(String zipCode);

    List<PostalCodeResponse> getLogs();
}
