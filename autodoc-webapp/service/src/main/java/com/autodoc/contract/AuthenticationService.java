package com.autodoc.contract;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


public interface AuthenticationService extends AuthenticationProvider {

    UsernamePasswordAuthenticationToken authenticate(Authentication authentication);
}
