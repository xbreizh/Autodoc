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

import javax.inject.Named;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Named
public class BillPdfCreatorImpl implements BillPdfCreator {
    private static final Logger LOGGER = Logger.getLogger(BillPdfCreatorImpl.class);
    private static final String ORIGIN = "/opt/tomcat/apache-tomcat-9.0.24/webapps/autodoctor/WEB-INF/views/html/bills/pdf/";
    String htmlFileName = "";
    String pdfFileName = "";
    String newHtmlFile = "";

    private static void copyFile(File source, File dest) throws IOException {
        LOGGER.info("copying files");
        LOGGER.info(source.exists());
        LOGGER.info(dest.exists());
        FileUtils.copyFile(source, dest);
        LOGGER.info(dest.exists());
    }

    public String generatePDFFromHTML(Bill bill) throws IOException {
        LOGGER.info("initiate name");
        initPathAndFileNames(bill);
        LOGGER.info("copy files");
        LOGGER.info("htmlFileName: " + htmlFileName);
        LOGGER.info("pdfFileName: " + pdfFileName);
        LOGGER.info("copiedHtmlFile: " + newHtmlFile);
        copyFile(new File(htmlFileName), new File(newHtmlFile));
        LOGGER.info("write content");
        writeContent(newHtmlFile, bill);
        LOGGER.info("convert");
        convertIntoPdf(newHtmlFile, pdfFileName);
        // LOGGER.info("remove");
        //removeHtmlCopy(newHtmlFile);
        int index = newHtmlFile.lastIndexOf("/");
        String fileName = newHtmlFile.substring(index + 1);
        LOGGER.info("filename: " + fileName);
        String fileToReturn = fileName.substring(0, fileName.lastIndexOf('.'));
        LOGGER.info("file to return: " + fileToReturn);
        return fileToReturn;
    }

    private void initPathAndFileNames(Bill bill) throws IOException {
        String fileName = generateFileName(bill);
        StringBuilder mainPath = new StringBuilder();

        mainPath.append(ORIGIN);

        // mainPath.append(getClass().getClassLoader().getResource("/bills/"));

        htmlFileName = mainPath + "template.html";
        pdfFileName = mainPath + fileName + ".pdf";
        newHtmlFile = mainPath + fileName + ".html";
        LOGGER.info("htmlFileName: "+htmlFileName);
        LOGGER.info("pdfFileName: "+pdfFileName);
        LOGGER.info("newHtmlFile: "+newHtmlFile);

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
        LOGGER.info("car to fill: " + bill.getCar());
        Map<String, String> input = new HashMap<>();
        input.put("[FIRSTNAME]", bill.getClient().getFirstName().toUpperCase());
        input.put("[LASTNAME]", bill.getClient().getLastName().toUpperCase());
        input.put("[EMPLOYEE]", bill.getEmployee().getLogin());
        String pattern = "EEEEE dd MMMMM yyyy HH:mm";
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(pattern, new Locale("fr", "FR"));

        String date = simpleDateFormat.format(bill.getDateReparation());
        input.put("[DATE]", date);
        input.put("[BILLID]", String.valueOf(bill.getId()));
        input.put("[PHONENUMBER]", bill.getClient().getPhoneNumber());
        input.put("[MANUFACTURER]", bill.getCar().getModel().getManufacturer().getName());
        input.put("[MODEL]", bill.getCar().getModel().getName());
        input.put("[ENGINE]", bill.getCar().getModel().getEngine());
        input.put("[FUELTYPE]", bill.getCar().getModel().getFuelType());
        input.put("[COLOR]", bill.getCar().getColor());
        input.put("[REGISTRATION]", bill.getCar().getRegistration());
        input.put("[KILOMETER]", String.valueOf(bill.getCar().getMileage()));
        input.put("[PAYMENT_TYPE]", bill.getPaymentType());
        input.put("[STATUS]", bill.getStatus());


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
            LOGGER.info("replacing");
            for (Map.Entry<String, String> entry : entries) {
                LOGGER.info("key: " + entry.getKey() + " value: " + entry.getValue());
                msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
            }
            LOGGER.info("msg: " + msg);

        } catch (NullPointerException e) {
            LOGGER.error("Issue while getting file");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cleanup(msg);
    }

    // removing all template items with []
    public String cleanup(String str) {
        LOGGER.info("cleaning message");
        return str.replaceAll("\\s*\\[[^\\]]*\\]\\s*", " ");
    }

    public void writeContent(String copiedHtmlFile, Bill bill) {
        LOGGER.info("writing content");
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

  /*  public boolean checkIfFileExists(String path) throws URISyntaxException, IOException {
        StringBuilder sb = new StringBuilder();
        File file = new File("$$$");
        sb.append(file.getAbsolutePath().replace("$$$", ""));
        sb.append("src/main/resources/");
        System.out.println("path: " + sb);
        return Files.exists(Paths.get(sb + "abc.txt"));
    }*/

/*    @Override
    public void saveAsPdf(Bill bill) throws IOException {
        LOGGER.info("trying to save as pdf");
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


    }*/

    private String generateFileName(Bill bill) {
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");

        String dateString = format.format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(bill.getCar().getRegistration());
        sb.append("_");
        sb.append(dateString);
        LOGGER.info("newFileName: "+sb.toString());
        return sb.toString();
    }

}
