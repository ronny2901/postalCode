package com.api.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostalCodeResponse {

    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;
}
