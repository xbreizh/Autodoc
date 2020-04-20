package com.autodoc.helper;

import com.autodoc.model.models.bill.Bill;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public interface Helper {

    String getConnectedLogin();

    String getConnectedToken();

    void addingPopup(ModelAndView mv, String error);

    void saveAsPdf(Bill bill) throws IOException;

    void generatePDFFromHTML(Bill bill) throws IOException;


}
