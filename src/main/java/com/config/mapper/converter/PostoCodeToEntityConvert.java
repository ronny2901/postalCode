package com.config.mapper.converter;

import com.api.dto.response.PostalCodeResponse;
import com.infrastructure.persistence.entity.PostalCodeEntity;
import org.modelmapper.AbstractConverter;

public class PostoCodeToEntityConvert extends AbstractConverter<PostalCodeResponse, PostalCodeEntity> {


    @Override
    protected PostalCodeEntity convert(PostalCodeResponse request) {
        return PostalCodeEntity.builder()
                .zipCode(request.getZipCode())
                .street(request.getStreet())
                .district(request.getDistrict())
                .state(request.getState())
                .build();
    }
}
