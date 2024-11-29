package com.ahmetaltun.securedoc.exception;

import com.ahmetaltun.securedoc.domain.response.ApiResponseType;
import com.ahmetaltun.securedoc.domain.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 29/11/2024
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponseType> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                        request.getRequestURI(),
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        Map.of()
                ));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponseType> handleValidation(ValidationException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        request.getRequestURI(),
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        Map.of()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseType> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, List<String>> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(
                                FieldError::getDefaultMessage,
                                Collectors.toList()
                        )
                ));

        Map<String, Object> errorDetails = Map.of(
                "validationErrors", validationErrors,
                "errorCount", validationErrors.values()
                        .stream()
                        .mapToInt(List::size)
                        .sum()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        request.getRequestURI(),
                        HttpStatus.BAD_REQUEST,
                        "Validation failed",
                        ex.getClass().getSimpleName(),
                        errorDetails
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseType> handleGeneral(Exception ex, HttpServletRequest request) {
        String message = Optional.ofNullable(ex.getMessage())
                .orElse("M: " + ex.getClass().getSimpleName());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(
                        request.getRequestURI(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        message,
                        ex.getClass().getSimpleName(),
                        Map.of()
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseType> handleNotFound(NotFoundException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(
                        request.getRequestURI(),
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        Map.of()
                ));
    }
}