package com.autodoc.helper.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.autodoc.model.models.person.employee.Employee;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;



public class PDFView extends AbstractPdfView {
    protected void buildPdfDocument(Map model,
                                    Document document, PdfWriter writer, HttpServletRequest req,
                                    HttpServletResponse resp) throws Exception {

        Employee employee = (Employee) model.get("command");

        Paragraph header = new Paragraph(new Chunk("Generate Pdf USing Spring Mvc", FontFactory.getFont(FontFactory.HELVETICA, 30)));
        Paragraph by = new Paragraph(new Chunk("Author " + employee.getFirstName() + " " + employee.getLastName(),FontFactory.getFont(FontFactory.HELVETICA, 20)));

        document.add(header);
        document.add(by);
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> map, com.lowagie.text.Document document, com.lowagie.text.pdf.PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

    }
}