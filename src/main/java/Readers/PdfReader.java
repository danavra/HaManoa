package Readers;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfReader extends AReader{

    /**
     *
     * @param pathName
     * @return
     */
    private String readPdfFile(String pathName) {
        try {
            //Loading an existing document
            File file = new File(pathName);
            PDDocument document = PDDocument.load(file);

            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();

            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);
//            System.out.println(text);


            //Closing the document
            document.close();
            return text;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    public String readFile(String path) {
        return readPdfFile(path);
    }
}
