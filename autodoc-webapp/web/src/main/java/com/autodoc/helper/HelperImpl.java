package com.autodoc.helper;


import com.autodoc.model.models.bill.Bill;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Named;
import java.io.*;

@Named
public class HelperImpl implements Helper {
    private static final int MAX_RESERVATION = 3;
    private static final Logger LOGGER = Logger.getLogger(HelperImpl.class);

    public void generatePDFFromHTML(Bill bill) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append(new File(".").getCanonicalPath());
        path.append("/src/main/resources/bills/");

        String fileName = generateFileName(bill);
        Document document = new Document();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document,
                    new FileOutputStream(path + "pdf/" + fileName + ".pdf"));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    new FileInputStream(path + "html/" + fileName + ".html"));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addingPopup(ModelAndView mv, String error) {
        mv.addObject("popup", true);
        mv.addObject("error", error);
    }

    @Override
    public void saveAsPdf(Bill bill) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append(new File(".").getCanonicalPath());
        path.append("/src/main/resources/pdf/");

        String fileName = generateFileName(bill);

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = null;
        try {
            contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.showText("Hello World");
            contentStream.endText();
            contentStream.close();

            document.save(path + fileName + ".pdf");
            LOGGER.info("document created");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("issue while creating the pdf: " + e.getMessage());
        }


    }

    private String generateFileName(Bill bill) {
        return "plakonow";
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
