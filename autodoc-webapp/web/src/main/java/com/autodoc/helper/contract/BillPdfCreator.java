package com.autodoc.helper.contract;

import com.autodoc.model.models.bill.Bill;

import java.io.IOException;

public interface BillPdfCreator {


    //  void saveAsPdf(Bill bill) throws IOException;

    String generatePDFFromHTML(Bill bill) throws IOException;


    void writeContent(String copiedHtmlFile, Bill bill) throws IOException;

    String cleanup(String str);

   // boolean checkIfFileExists(String path) throws URISyntaxException, IOException;
}
