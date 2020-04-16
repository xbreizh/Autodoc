package com.autodoc.business.impl;

import com.autodoc.business.contract.ConnectManager;
import com.autodoc.contract.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.inject.Named;

@Named
public class ConnectManagerImpl implements ConnectManager {

    private static Logger LOGGER = Logger.getLogger(ConnectManagerImpl.class);
    private AuthenticationService service;

    public ConnectManagerImpl(AuthenticationService service) {
        this.service = service;
    }

    @Override
    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) {
        LOGGER.info("getting authentication");
        return service.authenticate(authentication);

    }


}
