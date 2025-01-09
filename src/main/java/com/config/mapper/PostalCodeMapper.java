package com.config.mapper;

import com.api.dto.response.PostalCodeResponse;
import com.infrastructure.persistence.entity.PostalCodeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostalCodeMapper {

    PostalCodeEntity postalCodeToPostalCodeEntity(PostalCodeResponse dto);

    PostalCodeResponse postalCodeEntityToPostalCode(PostalCodeEntity entity);
}
