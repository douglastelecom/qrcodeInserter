package org.example;
import com.google.zxing.WriterException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import service.QRCode;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, WriterException {
        String link = "https://wp.info.ufrn.br/admin/portal-ufrn/wp-content/uploads/sites/3/2020/07/WhatsApp-Image-2020-07-16-at-12.40.42-3-300x283.jpeg";
        String inputPath = "./src/main/resources/pdf/historico.pdf";
        String outputPath ="./src/main/resources/pdf/historicoQRCode.pdf";
        float x = 51;
        float y = 1;
        float width = 50;
        float height = 50;

        //QRCode
        QRCode qrCode = new QRCode();
        PDDocument document = qrCode.stampQRCodeImage(link, inputPath, x,y,width,height);
        qrCode.savePDF(document, outputPath);
        document.close();
    }
}