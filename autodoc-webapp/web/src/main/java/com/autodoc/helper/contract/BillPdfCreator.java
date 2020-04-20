package com.autodoc.helper.contract;

import com.autodoc.model.models.bill.Bill;

import java.io.IOException;

public interface BillPdfCreator {


    void saveAsPdf(Bill bill) throws IOException;

    void generatePDFFromHTML(Bill bill) throws IOException;


    void writeContent(String copiedHtmlFile, Bill bill) throws IOException;
}
