package Readers;

import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PptxReader extends AReader{

    /**
     *
     * @param pathName
     * @return
     */
    private String readPptxFile(String pathName){
        try{
            File file = new File(pathName);
            XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(file));
            SlideShowExtractor<XSLFShape,XSLFTextParagraph> slideShowExtractor
                    = new SlideShowExtractor<XSLFShape,XSLFTextParagraph>(ppt);
            slideShowExtractor.setCommentsByDefault(true);
            slideShowExtractor.setMasterByDefault(true);
            slideShowExtractor.setNotesByDefault(true);
            String allTextContentInSlideShow = slideShowExtractor.getText();
//            System.out.println(allTextContentInSlideShow);
            return allTextContentInSlideShow;

            //<editor-fold desc="other way">
        /*
        // get slides
        for (XSLFSlide slide : ppt.getSlides()) {
            for (XSLFShape sh : slide.getShapes()) {
                // name of the shape
                String name = sh.getShapeName();
                // shapes's anchor which defines the position of this shape in the slide
                if (sh instanceof PlaceableShape) {
                    java.awt.geom.Rectangle2D anchor = ((PlaceableShape)sh).getAnchor();
                }

                if (sh instanceof XSLFConnectorShape) {
                    XSLFConnectorShape line = (XSLFConnectorShape) sh;
                    System.out.println("line");
                    // work with Line
                } else if (sh instanceof XSLFTextShape) {
                    XSLFTextShape shape = (XSLFTextShape) sh;
                    // work with a shape that can hold text
                    System.out.println(shape.getText());
                    System.out.println("text shape");

                } else if (sh instanceof XSLFPictureShape) {
                    XSLFPictureShape shape = (XSLFPictureShape) sh;
                    // work with Picture
                    System.out.println("picture shape");
                }
            }
        }
        */
            //</editor-fold>
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    public String readFile(String path) {
        return readPptxFile(path);
    }

}
