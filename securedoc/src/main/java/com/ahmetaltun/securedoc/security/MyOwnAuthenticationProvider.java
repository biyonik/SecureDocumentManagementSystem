package com.ahmetaltun.securedoc.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 30/11/2024
 */


@Component
@RequiredArgsConstructor
public class MyOwnAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) authentication;
        UserDetails userFromDb = userDetailsService.loadUserByUsername(user.getPrincipal().toString());
        String password = user.getCredentials().toString();
        if (password.equals(userFromDb.getPassword())) {
            return UsernamePasswordAuthenticationToken.authenticated(userFromDb, "[PASSWORD_PROTECTED]", userFromDb.getAuthorities());
        }
        throw new BadCredentialsException("Incorrect email or password!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
