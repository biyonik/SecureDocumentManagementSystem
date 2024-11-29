package com.ahmetaltun.securedoc.utils;

import com.ahmetaltun.securedoc.domain.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */


public class RequestUtils {
    private RequestUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Response successResponse(HttpServletRequest request, Map<String, Object> data, String message) {
        return Response.success(
                request.getRequestURI(),
                message,
                data
        );
    }

    public static Response errorResponse(
            HttpServletRequest request,
            HttpStatus status,
            String message,
            String exception,
            Map<String, Object> errorDetails) {

        return Response.error(
                request.getRequestURI(),
                status,
                message,
                exception,
                errorDetails
        );
    }
}
