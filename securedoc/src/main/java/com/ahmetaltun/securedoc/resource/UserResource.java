package com.ahmetaltun.securedoc.resource;

import com.ahmetaltun.securedoc.domain.Response;
import com.ahmetaltun.securedoc.dtorequest.UserRequest;
import com.ahmetaltun.securedoc.service.impl.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.ahmetaltun.securedoc.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 27/11/2024
 */


@RestController
@RequiredArgsConstructor
@RequestMapping(path = {"/user"})
public class UserResource {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(@RequestBody @Valid UserRequest user, HttpServletRequest request) {
        userService.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return ResponseEntity
                .created(getUri())
                .body(getResponse(request, emptyMap(), "Account created. Check your email to enable your account", CREATED));
    }

    private URI getUri() {
        return URI.create("");
    }
}
