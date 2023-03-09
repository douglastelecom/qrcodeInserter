package service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCode {

    public PDDocument stampQRCodeImage(PDDocument document, String uri, float x, float y, float width, float height) throws IOException, WriterException {
        PDPage page = document.getPage(document.getNumberOfPages()-1);
        File tempFile = createQRCodeImageTempFile(uri, 500);
        x = page.getMediaBox().getWidth() - x;
        PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
        PDImageXObject qrCodeXObject = PDImageXObject.createFromFileByContent(tempFile, document);
        contentStream.drawImage(qrCodeXObject,x,y,width,height);
//        contentStream.setStrokingColor(Color.BLACK);
//        contentStream.setLineWidth(2);
        contentStream.addRect(x, y, width, height);
//        contentStream.stroke();
        contentStream.close();
        return document;
    }

    public File createQRCodeImageTempFile(String link, int size) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, size, size);
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        File tempFile = File.createTempFile("qrCode", ".png");
        tempFile.deleteOnExit();
        ImageIO.write(qrCodeImage, "png", tempFile);
        return tempFile;
    }
    public PDDocument loadPDF(String path) throws IOException {
        File inputFile = new File(path);
        PDDocument document = PDDocument.load(inputFile);
        return document;
    }

    public void savePDF(PDDocument document, String path) throws IOException {
        File outputFile = new File(path);
        document.save(outputFile);
    }
//    public void addQrCode

}
