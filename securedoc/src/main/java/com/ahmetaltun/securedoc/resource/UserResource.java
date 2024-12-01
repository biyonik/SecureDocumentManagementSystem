package com.ahmetaltun.securedoc.resource;

import com.ahmetaltun.securedoc.domain.response.ApiResponseType;
import com.ahmetaltun.securedoc.domain.response.SuccessResponse;
import com.ahmetaltun.securedoc.dto.request.LoginRequest;
import com.ahmetaltun.securedoc.dto.request.UserRequest;
import com.ahmetaltun.securedoc.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    private final AuthenticationManager authenticationManager;

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

    @GetMapping("/verify/account")
    public ResponseEntity<ApiResponseType> verifyAccount(@RequestParam("token") String token, HttpServletRequest request) {
        userService.verifyAccount(token);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.of(
                        request.getRequestURI(),
                        "Account verified",
                        Map.of()
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseType> login(@RequestBody @Valid LoginRequest entity, HttpServletRequest request) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken unauthenticated = UsernamePasswordAuthenticationToken.unauthenticated(entity.getEmail(), entity.getPassword());
        Authentication authenticate = authenticationManager.authenticate(unauthenticated);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                  SuccessResponse.of(
                          request.getRequestURI(),
                          "Login successfully",
                          Map.of("user", authenticate)
                  ));
    }
}
