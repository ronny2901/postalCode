package com.config.mapper;

import com.api.dto.PostalCode;
import com.persistence.entity.PostalCodeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostalCodeMapper {

    PostalCodeEntity postalCodeToPostalCodeEntity(PostalCode dto);

    PostalCode postalCodeEntityToPostalCode(PostalCodeEntity entity);
}
