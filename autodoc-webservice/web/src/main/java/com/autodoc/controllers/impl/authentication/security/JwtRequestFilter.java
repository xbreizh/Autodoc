package com.autodoc.controllers.impl.authentication.security;

import com.autodoc.business.impl.authentication.JwtConnect;
import com.autodoc.business.impl.authentication.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Inject
    private JwtConnect jwtUserDetailsService;
    @Inject
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        logger.debug("req: " + requestTokenHeader);
        logger.debug("ff: " + request.getQueryString());
        String username = null;
        String jwtToken = null;
// JWT Token is in the form "Bearer token". Remove Bearer word and get
// only the Token

        logger.debug("request Header: " + requestTokenHeader);
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            logger.debug("here");

            jwtToken = requestTokenHeader.substring(7);
            logger.debug("jtoken: " + jwtToken);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                logger.debug("user: " + username);
            } catch (IllegalArgumentException e) {
                logger.debug("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.debug("JWT Token has expired");
            } catch (SignatureException e) {
                response.setHeader("exception", "Invalid token");
                logger.error("stuff");
            } catch (MalformedJwtException e) {
                response.setHeader("exception", "Malformed token");
                logger.error("malformed");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
// Once we get the token validate it.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
// if token is valid configure Spring Security to manually set
// authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
// After setting the Authentication in the context, we specify
// that the current user is authenticated. So it passes the
// Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}