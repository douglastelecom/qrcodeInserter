package service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;

public class ClickableLink {

    public void criarLink(PDDocument document, PDPage page, String uri){
        PDAnnotationLink link = new PDAnnotationLink();
        float x = 50;
        float y = 1;
        float width = 200;
        float height = 5;
        link.setRectangle(new PDRectangle(x, y, width, height));
        PDActionURI action = new PDActionURI();
        action.setURI(uri);
        link.setAction(action);

    }


}
