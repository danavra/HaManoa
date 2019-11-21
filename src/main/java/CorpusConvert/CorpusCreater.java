package CorpusConvert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.TreeMap;

public class CorpusCreater implements ICorpusCreater{
    Map<String, FileInfo> data;
    String output;
    String sysSeparator = System.getProperty("file.separator");


    public CorpusCreater(String outputPath){

        output=outputPath;
        data = new TreeMap<String, FileInfo>();
    }


    public void add(String fileName, FileInfo mData) {
        if(fileName == null || mData == null || fileName =="" || mData.getTxt() == "") {
            System.out.println("bracha input error - did not add the file to the corpus");
            return;
        }
        data.put(fileName,mData);
        if(fileName.endsWith("1---Preprocessing.pptx")){
            System.out.println("blabla");
        }
        System.out.println(fileName);
    }


    public void createCorpose() {
        if(data.isEmpty()){
            System.out.println("corpus is empty - file not created");
            return;
        }
        try {
            File folder = new File(output);
            folder.mkdir();
            String outputFileName = output.substring(output.lastIndexOf(sysSeparator));
            File corpus = new File(output+ outputFileName);


            FileWriter fw = new FileWriter(corpus);
            BufferedWriter bf = new BufferedWriter(fw);


            for (String fileName : data.keySet()) {
                bf.write("<DOC>\n" +
                        "<DOCNO> "+fileName+" </DOCNO>\n"
                        +"<F P=104> "+data.get(fileName).getCourse()+" </F>\n"
                        +"<TEXT>\n"
                        + data.get(fileName).getTxt()+" \n"
                        +"</TEXT>\n</DOC>\n");
            }

            bf.close();
        }catch (Exception e){
            System.out.println("bracha IO Exeption");
            System.out.println(e);
            return;
        }


        System.out.println("corpus created");

    }
}
