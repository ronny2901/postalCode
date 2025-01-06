package com.config.mapper.converter;

import com.api.dto.PostalCode;
import com.persistence.entity.PostalCodeEntity;

import org.modelmapper.AbstractConverter;

public class PostoCodeToEntityConvert extends AbstractConverter<PostalCode, PostalCodeEntity> {


    @Override
    protected PostalCodeEntity convert(PostalCode request) {
        return PostalCodeEntity.builder()
                .zipCode(request.getZipCode())
                .street(request.getStreet())
                .district(request.getDistrict())
                .state(request.getState())
                .build();
    }
}
