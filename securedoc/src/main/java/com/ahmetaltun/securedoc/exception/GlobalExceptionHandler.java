package com.ahmetaltun.securedoc.exception;

import com.ahmetaltun.securedoc.domain.Response;
import com.ahmetaltun.securedoc.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        ex.getClass().getSimpleName(),
                        new HashMap<>()
                ));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> handleValidation(ValidationException ex, HttpServletRequest request) {
        System.out.println(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RequestUtils.errorResponse(
                        request,
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        new HashMap<>()
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        // Hataları field bazlı gruplayalım
        Map<String, List<String>> validationErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();

            validationErrors.computeIfAbsent(fieldName, k -> new ArrayList<>())
                    .add(errorMessage);
        });

        // Daha zengin bir hata yanıtı oluşturalım
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("validationErrors", validationErrors);
        errorDetails.put("errorCount", validationErrors.values()
                .stream()
                .mapToInt(List::size)
                .sum());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(RequestUtils.errorResponse(
                        request,
                        HttpStatus.BAD_REQUEST,
                        "Validation failed",
                        ex.getClass().getSimpleName(),
                        errorDetails
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleGeneral(Exception ex, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RequestUtils.errorResponse(
                        request,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage() == null ? "M: "+ex.getClass().getSimpleName() : ex.getMessage(),
                        ex.getClass().getSimpleName(),
                        new HashMap<>()
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
                        ex.getClass().getSimpleName(),
                        new HashMap<>()
                ));
    }
}