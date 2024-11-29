package com.ahmetaltun.securedoc.resource;

import com.ahmetaltun.securedoc.domain.Response;
import com.ahmetaltun.securedoc.dtorequest.UserRequest;
import com.ahmetaltun.securedoc.service.IUserService;
import com.ahmetaltun.securedoc.service.impl.UserServiceImpl;
import com.ahmetaltun.securedoc.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
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
    public ResponseEntity<Response> register(@RequestBody @Valid UserRequest user, HttpServletRequest request) {
        userService.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        return ResponseEntity
                .created(URI.create("/user/" + user.getEmail()))
                .body(RequestUtils.successResponse(
                        request,
                        Map.of("email", user.getEmail()),
                        "Account created. Check your email to enable your account"
                ));
    }
}
