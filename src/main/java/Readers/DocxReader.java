package Readers;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class DocxReader extends AReader {

    /**
     *
     * @param sFile
     */
    private String readDocxFile(String sFile) {
        try {
            File file = new File(sFile);
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            XWPFDocument document = new XWPFDocument(fis);

            List<XWPFParagraph> paragraphs = document.getParagraphs();
            String ans = "";


            for (XWPFParagraph para : paragraphs) {
                ans += (para.getText() + "\n");
            }
            fis.close();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String readFile(String path) {
        return readDocxFile(path);
    }

}