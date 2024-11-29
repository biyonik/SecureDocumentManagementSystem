package com.ahmetaltun.securedoc.resource;

import com.ahmetaltun.securedoc.domain.response.ApiResponseType;
import com.ahmetaltun.securedoc.domain.response.SuccessResponse;
import com.ahmetaltun.securedoc.dto.request.UserRequest;
import com.ahmetaltun.securedoc.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserResource {
    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseType> register(@RequestBody @Valid UserRequest user, HttpServletRequest request) {
        userService.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(
                        request.getRequestURI(),
                        "Account created successfully",
                        Map.of("email", user.getEmail())
                ));
    }
}
