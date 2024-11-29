package com.ahmetaltun.securedoc.domain.response;

import org.springframework.http.HttpStatus;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 29/11/2024
 */

public sealed interface ApiResponseType permits ErrorResponse, SuccessResponse {
    String time();
    int code();
    String path();
    HttpStatus status();
    String message();
}
