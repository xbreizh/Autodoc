package com.autodoc.impl;

import com.autodoc.contract.AuthenticationService;
import com.autodoc.contract.GlobalService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

public abstract class HelperTest {
    private static final Logger LOGGER = Logger.getLogger(HelperTest.class);
    protected String token;
    private AuthenticationService authenticationService;
    private Authentication authToken;
    private GlobalService globalService;

    public String getToken() {
        return token;
    }

    @BeforeEach
    void initToken() {
        authToken = new UsernamePasswordAuthenticationToken("lmolo", "password", Arrays.asList(new SimpleGrantedAuthority("user")));
        authenticationService = new AuthenticationServiceImpl();
        SecurityContextHolder.getContext().setAuthentication(authToken);
        String token = authenticationService.authenticate(authToken).getDetails().toString();
        String newToken = token.replace("{\"token\":\"", "");
        this.token = newToken.replace("\"}", "");
    }

    @BeforeEach
    void resetDBTest() {
        globalService = new GlobalServiceImpl();
        //globalService.reset();
        LOGGER.info("db has been reset!");

    }


}
