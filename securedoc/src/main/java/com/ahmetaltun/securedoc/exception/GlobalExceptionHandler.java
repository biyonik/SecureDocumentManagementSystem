package com.ahmetaltun.securedoc.exception;

import com.ahmetaltun.securedoc.domain.Response;
import com.ahmetaltun.securedoc.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 29/11/2024
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RequestUtils.errorResponse(
                        request,
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        ex.getClass().getSimpleName()
                ));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handleValidation(ValidationException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RequestUtils.errorResponse(
                        request,
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        ex.getClass().getSimpleName()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneral(Exception ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RequestUtils.errorResponse(
                        request,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "An unexpected error occurred",
                        ex.getClass().getSimpleName()
                ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response> handleNotFound(NotFoundException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RequestUtils.errorResponse(
                        request,
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        ex.getClass().getSimpleName()
                ));
    }
}