package com.autodoc.spring.controller.impl;


import com.autodoc.helper.contract.BillPdfCreator;
import com.autodoc.helper.impl.BillPdfCreatorImpl;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.person.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class GlobalControllerTest {

    BillPdfCreator pdfCreator;
    Bill bill;

    @BeforeEach
    void init() {
        pdfCreator = new BillPdfCreatorImpl();
        bill = new Bill();
        Client client = new Client();
        client.setFirstName("john");
        client.setLastName("Butler");
        bill.setClient(client);
    }


    @Test
    void saveAsPdf() throws IOException {
        pdfCreator.generatePDFFromHTML(bill);

    }

    @Test
    void copyFile() throws IOException {
        pdfCreator.generatePDFFromHTML(bill);

    }

/*    @Test
    void writeContent() throws IOException {

        helper.writeContent( "");

    }*/

}
