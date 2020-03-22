package com.autodoc.impl;

import com.autodoc.contract.AuthenticationService;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

@Named
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String ROLE = "USER";
    private static final String BASE_URL = "http://localhost:8087/autodoc/";
    private static JSONObject personJsonObject;
    private static Logger LOGGER = Logger.getLogger(AuthenticationServiceImpl.class);

    @Override
    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) {

        LOGGER.info("trying to authenticate");
        String token = "";
        String exception = "";
        String login = authentication.getName().toUpperCase();
        String password = (String) authentication.getCredentials();

        RestTemplate restTemplate;

        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        personJsonObject = new JSONObject();
        personJsonObject.put("username", login);
        personJsonObject.put("password", password);

        HttpEntity<String> request =
                new HttpEntity<String>(personJsonObject.toString(), headers);

        try {
            token = restTemplate.postForObject(BASE_URL + "/authenticate", request, String.class);
        } catch (Exception e) {
            throw new
                    BadCredentialsException("External system authentication failed");
        }

        LOGGER.info(authentication.getPrincipal().toString());


        LOGGER.info("token found: " + token);

        if (!token.equals("wrong credentials") && exception.isEmpty()) {

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(login, token, buildUserAuthority());

            LOGGER.info("trucko: " + auth.getAuthorities());
            LOGGER.info("cred: " + auth.getCredentials());
            LOGGER.info("login: " + auth.getName());
            LOGGER.info("principal: " + auth.getPrincipal());

            auth.setDetails(token);
            return auth;
        } else {
            throw new
                    BadCredentialsException("External system authentication failed");

        }
    }


    public List<GrantedAuthority> buildUserAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority(ROLE));
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
