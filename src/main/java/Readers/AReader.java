package Readers;

public abstract class AReader {

    public abstract String readFile(String path);

    static public void openFile(String path) {
        try {
            String sPath = path.substring(path.indexOf(".")+2);
            sPath = sPath.substring(0, sPath.indexOf(" "));
            Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+sPath);
            p.waitFor();
        }
        catch (Exception e){
            System.out.println("PowerPoint open file exception!");
        }
    }

    static public AReader readerFactory(String fileName){
        if (fileName.contains(".doc")){
            return new DocxReader();
        }
        else if (fileName.contains(".ppt")){
            return new PptxReader();
        }
        else if (fileName.contains(".pdf")){
            return new PdfReader();
        }
        else return null;
    }
}
