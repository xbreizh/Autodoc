package com.autodoc.business.contract;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.inject.Named;

@Named
public interface ConnectManager {

    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication);
}
