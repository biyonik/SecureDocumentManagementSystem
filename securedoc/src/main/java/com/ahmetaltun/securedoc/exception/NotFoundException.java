package com.ahmetaltun.securedoc.exception;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 29/11/2024
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entity, String field, String value) {
        super(String.format("%s not found with %s: %s", entity, field, value));
    }

    public NotFoundException(String message) {
        super(message);
    }
}