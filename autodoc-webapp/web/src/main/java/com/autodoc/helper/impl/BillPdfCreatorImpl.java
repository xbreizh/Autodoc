package com.autodoc.helper.impl;

import com.autodoc.helper.contract.BillPdfCreator;
import com.autodoc.model.models.bill.Bill;
import com.autodoc.model.models.pieces.Piece;
import com.autodoc.model.models.tasks.Task;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BillPdfCreatorImpl implements BillPdfCreator {
    private static final Logger LOGGER = Logger.getLogger(BillPdfCreatorImpl.class);
    private static final String ORIGIN = "/src/main/resources/bills/";
    String htmlFileName = "";
    String pdfFileName = "";
    String copiedHtmlFile = "";

    private static void copyFile(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }

    public void generatePDFFromHTML(Bill bill) throws IOException {
        initPathAndFileNames(bill);

        copyFile(new File(htmlFileName), new File(copiedHtmlFile));
        writeContent(copiedHtmlFile, bill);
        convertIntoPdf(copiedHtmlFile, pdfFileName);
        removeHtmlCopy(copiedHtmlFile);

    }

    private void initPathAndFileNames(Bill bill) throws IOException {
        String fileName = generateFileName(bill);
        StringBuilder mainPath = new StringBuilder();
        mainPath.append(new File(".").getCanonicalPath());
        mainPath.append(ORIGIN);

        htmlFileName = mainPath + "html/" + fileName + ".html";
        pdfFileName = mainPath + "pdf/" + fileName + ".pdf";
        copiedHtmlFile = mainPath + "html/" + fileName + "Copy.html";
    }

    private void removeHtmlCopy(String copiedHtmlFile) {
        try {
            Files.delete(Paths.get(copiedHtmlFile));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void convertIntoPdf(String htmlSource, String pdfDestination) {
        Document document = new Document();
        PdfWriter writer = null;
        try {
            writer = PdfWriter.getInstance(document,
                    new FileOutputStream(pdfDestination));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                    new FileInputStream(htmlSource));
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String fillTemplate(String filePath, Bill bill) {
        Map<String, String> input = new HashMap<>();
        input.put("[FIRSTNAME]", bill.getClient().getFirstName().toUpperCase());
        input.put("[LASTNAME]", bill.getClient().getLastName().toUpperCase());
        input.put("[EMPLOYEE]", bill.getEmployee().getLogin());
        input.put("[DATE]", bill.getDateReparation().toString());
        input.put("[BILLID]", String.valueOf(bill.getId()));
        input.put("[PHONENUMBER]", bill.getClient().getPhoneNumber());
        input.put("[MANUFACTURER]", bill.getCar().getModel().getManufacturer().getName());
        input.put("[MODEL]", bill.getCar().getModel().getName());
        input.put("[ENGINE]", bill.getCar().getModel().getEngine());
        input.put("[FUELTYPE]", bill.getCar().getModel().getFuelType());
        input.put("[REGISTRATION]", bill.getCar().getRegistration());
        input.put("[KILOMETER]", "tobeFilled");

        double totalTask = 0;
        if (!bill.getTasks().isEmpty()) {
            int i = 1;
            for (Task task : bill.getTasks()) {
                double rate = 17.5;
                double total = task.getEstimatedTime() * rate;
                input.put("[WORK" + i + "]", task.getName());
                input.put("[HOURS" + i + "]", String.valueOf(task.getEstimatedTime()));
                input.put("[RATE" + i + "]", String.valueOf(rate));
                input.put("[AMOUNTWORK" + i + "]", String.valueOf(total));
                i++;
                totalTask += total;
            }
        }
        input.put("[SUBTOTALWORK]", String.valueOf(totalTask));

        double totalPieces = 0;

        if (!bill.getPieces().isEmpty()) {
            int i = 1;
            for (Piece piece : bill.getPieces()) {
                input.put("[PIECETYPE" + i + "]", piece.getPieceType().getName());
                input.put("[PIECE" + i + "]", String.valueOf(piece.getName()));
                input.put("[AMOUNTPIECE" + i + "]", String.valueOf(piece.getSellPrice()));
                i++;
                totalPieces += piece.getSellPrice();
            }
        }
        input.put("[COMMENTS]", bill.getComments());
        input.put("[SUBTOTALPIECE]", String.valueOf(totalPieces));
        input.put("[TVA]", String.valueOf(bill.getVat()));
        input.put("[TOTALBEFOREDISCOUNT]", String.valueOf(totalTask + totalPieces));
        input.put("[DISCOUNT]", String.valueOf(bill.getDiscount()));
        double total = (totalTask + totalPieces) - (totalTask + totalPieces) * (bill.getDiscount() / 100);
        input.put("[TOTAL]", String.valueOf(total));

        File file = new File(filePath);
        LOGGER.info("replacing values for keys");
        String msg = null;
        try {
            msg = readContentFromFile(file);


            Set<Map.Entry<String, String>> entries = input.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
            }
            System.out.println("msg: " + msg);

        } catch (NullPointerException e) {
            LOGGER.error("Issue while getting file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //cleanup(msg);

        return cleanup(msg);
    }

    public String cleanup(String str) {
        //str = str.replaceAll("\\[.*\\]", "");
        // removing parenthesis and everything inside them, works for (),[] and {}
        return str.replaceAll("\\s*\\[[^\\]]*\\]\\s*", " ");
    }

    public void writeContent(String copiedHtmlFile, Bill bill) {
        String msg = fillTemplate(copiedHtmlFile, bill);
        try {
            Files.write(Paths.get(copiedHtmlFile), msg.getBytes());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    String readContentFromFile(File file) throws IOException {
        LOGGER.info("trying to read content from html file and returning a String");
        StringBuilder contents = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contents.append(line);
            }
        }

        LOGGER.info("html file converted ok");
        return contents.toString();
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

}
