package com.ahmetaltun.securedoc.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */

public record Response(
        String time,
        int code,
        String path,
        HttpStatus status,
        String message,
        String exception,
        Map<String, Object> data,
        Map<String, Object> errors
) {
    public static Response success(String path, String message, Map<String, Object> data) {
        return new Response(
                LocalDateTime.now().toString(),
                HttpStatus.OK.value(),
                path,
                HttpStatus.OK,
                message,
                null,
                data,
                null
        );
    }

    public static Response error(
            String path,
            HttpStatus status,
            String message,
            String exception,
            Map<String, Object> errorDetails) {

        return new Response(
                LocalDateTime.now().toString(),
                status.value(),
                path,
                status,
                message,
                exception,
                null,
                errorDetails
        );
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ResponseBuilder {
        private final String time = LocalDateTime.now().toString();
        private int code;
        private String path;
        private HttpStatus status;
        private String message;
        private String exception;
        private Map<String, Object> data;
        private Map<String, Object> errors;

        public ResponseBuilder code(int code) {
            this.code = code;
            return this;
        }

        public ResponseBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder exception(String exception) {
            this.exception = exception;
            return this;
        }

        public ResponseBuilder data(Map<String, Object> data) {
            this.data = data;
            return this;
        }

        public ResponseBuilder errors(Map<String, Object> errors) {
            this.errors = errors;
            return this;
        }

        public Response build() {
            return new Response(time, code, path, status, message, exception, data, errors);
        }
    }
}
