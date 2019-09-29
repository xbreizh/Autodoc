/*
package com.autodoc.controllers.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.inject.Named;
import java.util.ArrayList;

@Named
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("name found: "+name);
        System.out.println("pwd fgound: "+password);
        if(name.equals("paul")){
            System.out.println("authenticated: "+name);
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        }

        */
/*if (shouldAuthenticateAgainstThirdPartySystem()) {

            // use the credentials
            // and authenticate against the third-party system
            return new UsernamePasswordAuthenticationToken(
                    name, password, new ArrayList<>());
        } else {
            return null;
        }*//*

        System.out.println("shout out");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}*/
