package com.autodoc.controllers.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        PrintWriter writer = response.getWriter();
        //writer.println("HTTP Status 401 - " + authException.getMessage());
        writer.println(HttpStatus.UNAUTHORIZED+" - " + response.getHeader("exception"));
        System.out.println("again: "+response.getHeader("test"));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());


    }
}