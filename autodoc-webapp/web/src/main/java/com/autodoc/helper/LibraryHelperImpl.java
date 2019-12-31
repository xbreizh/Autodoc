package com.autodoc.helper;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
public class LibraryHelperImpl implements LibraryHelper {
    private static final int MAX_RESERVATION = 3;
    private static Logger LOGGER = Logger.getLogger(LibraryHelperImpl.class);

    @Override
    public void addingPopup(ModelAndView mv, String error) {
        mv.addObject("popup", true);
        mv.addObject("error", error);
    }


    @Override
    public Map<String, String> generateSearchMap(String isbn, String author, String title) {
        Map<String, String> criteria = new HashMap<>();
        criteria.put("ISBN", isbn);
        criteria.put("TITLE", title);
        criteria.put("AUTHOR", author);

        return criteria;

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
