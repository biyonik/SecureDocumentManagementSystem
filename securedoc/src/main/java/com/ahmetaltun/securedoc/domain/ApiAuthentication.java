package com.ahmetaltun.securedoc.domain;


import com.ahmetaltun.securedoc.dto.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 01/12/2024
 */

public class ApiAuthentication extends AbstractAuthenticationToken {
    private static final String PASSWORD_PROTECTED = "[PASSWORD_PROTECTED]";
    private static final String EMAIL_PROTECTED = "[EMAIL_PROTECTED]";
    private User user;
    private String email;
    private String password;
    private boolean authenticated;

    private ApiAuthentication(String email, String password) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.user = user;
        this.password = password;
        this.email = email;
        this.authenticated = false;
    }

    private ApiAuthentication(User user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.user = user;
        this.password = PASSWORD_PROTECTED;
        this.email = EMAIL_PROTECTED;
        this.authenticated = true;
    }

    public static ApiAuthentication unauthenticated(String email, String password) {
        return new ApiAuthentication(email, password);
    }
    public static ApiAuthentication authenticated(User user, Collection<? extends GrantedAuthority> authorities) {
        return new ApiAuthentication(user, authorities);
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        throw new UnsupportedOperationException("You cannot set authentication!");
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public Object getCredentials() {
        return PASSWORD_PROTECTED;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}
