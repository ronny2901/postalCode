package com.api.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRes {
    HttpStatus httpStatus;
    String message;
}