package com.autodoc.helper.contract;

import org.springframework.web.servlet.ModelAndView;

public interface AuthenticationHelper {

    String getConnectedLogin();

    String getConnectedToken();

    void addingPopup(ModelAndView mv, String error);


}
