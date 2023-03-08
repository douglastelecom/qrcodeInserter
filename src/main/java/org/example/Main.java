package org.example;
import com.google.zxing.WriterException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import service.QRCode;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, WriterException {
        //QRCode
        QRCode qrCode = new QRCode();
        File tempFile = qrCode.createQRCodeImageTempFile("https://wp.info.ufrn.br/admin/portal-ufrn/wp-content/uploads/sites/3/2020/07/WhatsApp-Image-2020-07-16-at-12.40.42-3-300x283.jpeg", 200);
        PDDocument document = qrCode.loadPDF("/home/douglasfernandes/Downloads/output.pdf");
        PDPage page = document.getPage(document.getNumberOfPages()-1);
        float x = page.getMediaBox().getWidth() - 51;
        float y = 1;
        float width = 50;
        float height = 50;
        qrCode.stampQRCodeImage(document, page, tempFile, x, y, width, height);
        qrCode.savePDF(document, "/home/douglasfernandes/Downloads/output2.pdf");

        //Clickable Link
    }

}