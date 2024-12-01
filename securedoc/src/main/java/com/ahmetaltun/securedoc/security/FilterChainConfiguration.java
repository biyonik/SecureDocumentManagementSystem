package com.ahmetaltun.securedoc.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * @author Ahmet Altun
 * @version 1.0
 * @email ahmet.altun60@gmail.com
 * @since 30/11/2024
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class FilterChainConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/user/login").permitAll()
                                .anyRequest().authenticated()
                ).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        MyOwnAuthenticationProvider myOwnAuthenticationProvider = new MyOwnAuthenticationProvider(userDetailsService);
        return new ProviderManager(myOwnAuthenticationProvider);
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails firstUser = User.withDefaultPasswordEncoder()
//                .username("ahmet.altun60@gmail.com")
//                .password("{noop}Az123456+-")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(List.of(firstUser));
//    }

    @Bean
    public UserDetailsService inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("ahmet.altun60@gmail.com")
                        .password("Az123456+-")
                        .roles("USER")
                        .build()
        );
    }
}
