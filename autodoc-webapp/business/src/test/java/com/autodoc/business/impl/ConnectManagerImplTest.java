package com.autodoc.business.impl;

import com.autodoc.contract.AuthenticationService;
import com.autodoc.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConnectManagerImplTest {

    String token;
    private Authentication authToken;
    private String login = "lmolo";
    private String password = "password";
    private String role = "user";
    private AuthenticationService service;

    @BeforeEach
    void getToken() {
        authToken = new UsernamePasswordAuthenticationToken("lmolo", "password", Arrays.asList(new SimpleGrantedAuthority("user")));
        service = new AuthenticationServiceImpl();
        SecurityContextHolder.getContext().setAuthentication(authToken);
        String token = service.authenticate(authToken).getDetails().toString();
        String newToken = token.replace("{\"token\":\"", "");
        this.token = newToken.replace("\"}", "");
    }

    @Test
    @DisplayName("should return a token if credentials are valid")
    void authenticate() {

        assertNotNull(token);
    }


    @Test
    @DisplayName("should return a token if credentials are valid")
    void authenticate1() {
        Authentication auth2 = new UsernamePasswordAuthenticationToken("john", "abc123", Arrays.asList(new SimpleGrantedAuthority("admin")));
        assertThrows(BadCredentialsException.class, () -> service.authenticate(auth2));
    }
}