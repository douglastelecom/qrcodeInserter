package org.example;
import com.google.zxing.WriterException;
import org.apache.pdfbox.pdmodel.PDDocument;
import service.Hyperlink;
import service.QRCode;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, WriterException {

        //definir variáveis
        String uri = "https://www.tre-rn.jus.br/jurisprudencia/ementario/ementarios-tematicos";
        String inputPath = "./src/main/resources/pdf/historico.pdf";
        String outputPath ="./src/main/resources/pdf/historicoQRCode.pdf";

        float xQRCode = 86;
        float yQRCode = 1;
        float widthQRCode = 75;
        float heightQRCode = 75;

        float xHyperlink = 70 ;
        float yHyperlink = 15;
        float widthHyperlink = 50;
        float heightHyperlink = 20;

        //carregar pdf
        PDDocument document = PDDocument.load(new File(inputPath));

        //Adicionar QRCode
        QRCode qrCode = new QRCode();
        document = qrCode.stampQRCodeImage(document, uri,xQRCode, yQRCode, widthQRCode, heightQRCode);

        //Adicionar Hyperlink
        Hyperlink clickableLink = new Hyperlink();
        document = clickableLink.addHyperlink(document, xHyperlink, yHyperlink, widthHyperlink, heightHyperlink, uri);

        //Salvar PDF
        document.save(new File(outputPath));
        document.close();
    }
}