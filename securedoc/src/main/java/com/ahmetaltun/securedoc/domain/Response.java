package com.ahmetaltun.securedoc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public record Response(String time, int code, String path, HttpStatus status, String message, String exception, Map<?, ?> data) { }
