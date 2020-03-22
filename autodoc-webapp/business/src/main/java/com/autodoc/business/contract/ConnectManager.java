package com.autodoc.business.contract;

import org.springframework.security.authentication.AuthenticationProvider;

import javax.inject.Named;

@Named
public interface ConnectManager extends AuthenticationProvider {

    // public UsernamePasswordAuthenticationToken authenticate(Authentication authentication);
}
