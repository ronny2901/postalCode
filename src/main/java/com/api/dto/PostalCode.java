package com.api.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostalCode {

    private String zipCode;
    private String street;
    private String district;
    private String city;
    private String state;
}
