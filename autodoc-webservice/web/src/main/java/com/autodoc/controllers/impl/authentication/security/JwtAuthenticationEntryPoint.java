package com.autodoc.controllers.impl.authentication.security;

import org.apache.log4j.Logger;
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
    private static final Logger LOGGER = Logger.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        LOGGER.debug("checking authentication");
        PrintWriter writer = response.getWriter();
        writer.println(HttpStatus.UNAUTHORIZED + " - " /*+ response.getHeader("exception")*/ +
                "\n" + /*authException.getMessage()*/"Authentication failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());


    }
}