package com.autodoc.business.impl;

import com.autodoc.business.contract.ConnectManager;
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

    private ConnectManager manager;
    private Authentication authToken;
    private String login = "lmolo";
    private String password = "password";
    private String role = "user";

    @BeforeEach
    void init() {
        manager = new ConnectManagerImpl();
        authToken = new UsernamePasswordAuthenticationToken(login, password, Arrays.asList(new SimpleGrantedAuthority(role)));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    @Test
    @DisplayName("should return a token if credentials are valid")
    void authenticate() {
        String token = manager.authenticate(authToken).getDetails().toString();
        String newToken = token.replace("{\"token\":\"", "");
        newToken = newToken.replace("\"}", "");
        assertNotNull(newToken);
    }


    @Test
    @DisplayName("should return a token if credentials are valid")
    void authenticate1() {
        Authentication auth2 = new UsernamePasswordAuthenticationToken("john", "abc123", Arrays.asList(new SimpleGrantedAuthority("admin")));
        assertThrows(BadCredentialsException.class, () -> manager.authenticate(auth2));
    }
}