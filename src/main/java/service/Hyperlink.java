package service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;

import java.io.IOException;

public class Hyperlink {

//    public PDDocument teste(PDDocument document){
//        Paragraph paragraph = new Paragraph
//    }

    public PDDocument addHyperlink(PDDocument document, float x, float y, float width, float height, String uri) throws IOException {
        document = turnClickable(document, x, y, width, height, uri);
        document = writeURI(document, x, y, uri);
        return document;
    }

    public PDDocument turnClickable(PDDocument document, float x, float y, float width, float height, String uri) throws IOException {
        PDPage page = document.getPage(document.getNumberOfPages()-1);
        PDAnnotationLink link = new PDAnnotationLink();
        PDActionURI action = new PDActionURI();
        action.setURI(uri);
        link.setAction(action);
        PDRectangle position = new PDRectangle();
        position.setLowerLeftX(x);
        position.setLowerLeftY(y+3);
        position.setUpperRightX(page.getMediaBox().getUpperRightX()-90);
        position.setUpperRightY(37);
        link.setRectangle(position);
        link.setBorderStyle(new PDBorderStyleDictionary());
        page.getAnnotations().add(link);
        return document;
    }

    public PDDocument writeURI(PDDocument document, float x, float y, String uri) throws IOException {
        PDPage page = document.getPage(document.getNumberOfPages()-1);
        PDFont fontURI = PDType1Font.HELVETICA_BOLD;
        PDFont fontText = PDType1Font.HELVETICA;
        String text = "Acesse o link abaixo ou escaneie o QR Code para validar o documento.";
        int fontSizeURI = 10;
        int fontSizeText = 12;
        float widthURI = fontURI.getStringWidth(uri) / 1000 * fontSizeURI;
        float widthText = fontText.getStringWidth(text) / 1000 * fontSizeText;

        PDPageContentStream contentStreamText = new PDPageContentStream(document,  page, PDPageContentStream.AppendMode.APPEND, false);
        contentStreamText.beginText();
        contentStreamText.newLineAtOffset((page.getMediaBox().getWidth() - widthText) / 2, 40);
        contentStreamText.setFont(fontText, fontSizeText);
        contentStreamText.showText(text);
        contentStreamText.endText();
        contentStreamText.close();

        PDPageContentStream contentStreamURI = new PDPageContentStream(document,  page, PDPageContentStream.AppendMode.APPEND, false);
        contentStreamURI.beginText();
        contentStreamURI.newLineAtOffset((page.getMediaBox().getWidth() - widthURI) / 2, 25);
        contentStreamURI.setFont(fontURI, fontSizeURI);
        contentStreamURI.showText(uri);
        contentStreamURI.endText();
        contentStreamURI.close();
        return document;
    }

}
