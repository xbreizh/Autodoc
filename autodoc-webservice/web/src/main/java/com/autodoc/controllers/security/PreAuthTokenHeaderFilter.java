package com.autodoc.controllers.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

public class PreAuthTokenHeaderFilter
        extends AbstractPreAuthenticatedProcessingFilter {

    private String authHeaderName;

    public PreAuthTokenHeaderFilter(String authHeaderName) {
        this.authHeaderName = authHeaderName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {


        System.out.println("head: " + request.getHeaderNames().toString());
        System.out.println("un: "+authHeaderName);
        System.out.println("deux: "+request.getQueryString());


        return request.getHeader(authHeaderName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {

        System.out.println("head: " + request.getHeaderNames().toString());
        System.out.println("un: "+authHeaderName);
        System.out.println("deux: "+request.getHeader(authHeaderName));
        System.out.println("there");
        return "N/A";
    }
}