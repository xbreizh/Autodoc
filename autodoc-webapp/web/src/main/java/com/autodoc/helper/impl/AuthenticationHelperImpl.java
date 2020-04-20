package com.autodoc.helper.impl;


import com.autodoc.helper.contract.AuthenticationHelper;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Named;

@Named
public class AuthenticationHelperImpl implements AuthenticationHelper {
    private static final Logger LOGGER = Logger.getLogger(BillPdfCreatorImpl.class);


    @Override
    public void addingPopup(ModelAndView mv, String error) {
        mv.addObject("popup", true);
        mv.addObject("error", error);
    }


    //  Authentication

    @Override
    public String getConnectedLogin() {
        Authentication authentication = getAuthentication();
        LOGGER.info("get connected login " + authentication.getPrincipal().toString());
        return authentication.getPrincipal().toString();
    }


    @Override
    public String getConnectedToken() {
        LOGGER.info("get connected token");
        Authentication authentication = getAuthentication();
        String token = authentication.getDetails().toString();
        String newToken = cleanToken(token);
        LOGGER.info("new token: " + newToken);
        return newToken;
    }

    private String cleanToken(String token) {
        String newToken = token.replace("{\"token\":\"", "");
        newToken = newToken.replace("\"}", "");
        return newToken;
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LOGGER.info("getting authentication: " + authentication.getName());
        return authentication;
    }
}
