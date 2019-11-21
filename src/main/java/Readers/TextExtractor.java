package Readers;

import CorpusConvert.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextExtractor {
    String sysSeparator = System.getProperty("file.separator");
    File mainDir; //the directory where all the docx, pptx, pdf files are in
    String outputDir; //the directory where all the text files will be written into

    public TextExtractor(String ourputPath) {
        this.mainDir = new File(ourputPath + sysSeparator + "MoodleFiles");
        outputDir = ourputPath + sysSeparator + "c2txt";
        File convertedDir = new File(outputDir);
        convertedDir.mkdirs();
        createStopWords(System.getProperty("user.dir")+sysSeparator+"stop_words.txt");
    }

    private void createStopWords(String stopWordsFile){
        File readable = new File(stopWordsFile);
        File writable = new File(outputDir + sysSeparator + "stop_words.txt");
        try {
            FileReader fileReader = new FileReader(readable);
            FileWriter fileWriter = new FileWriter(writable);
            BufferedReader reader = new BufferedReader(fileReader);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            while (reader.ready()){
                writer.write(reader.readLine());
            }
            writer.close();
            reader.close();
            fileWriter.close();
            fileReader.close();
        }
        catch (Exception e){
            System.out.println("Creating Stop Words in TextExecutor");
        }

    }

    public void walkThroughPath(String dir){
        String sDir = mainDir + sysSeparator + dir;
        ICorpusCreater iCorpusCreater = new CorpusCreater(outputDir + sysSeparator + dir);
        try (Stream<Path> paths = Files.walk(Paths.get(sDir))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    FileInfo fileInfo = getFileInfo(filePath.toFile(), dir);
                    iCorpusCreater.add(filePath.toString(), fileInfo);
                }
            });
        } catch (IOException e) {
            System.out.println("prob");
            e.printStackTrace();
        }
        finally {
            iCorpusCreater.createCorpose();
        }
    }

    /**
     * read the given directory
     * @return a list of string array so: ans[0] = course_name, ans[1] = file_name, ans[2] = file_text
     */
    public String readDirectories(){
        try {
            File[] subDirs = mainDir.listFiles();
//            double  n = 1;
//            double size = subDirs.length+1;
//            int progress = 0;
//            int last = -1;
            for (File subDir: subDirs){
                if (subDir.isDirectory()){
//                    progress = (int)Math.floor((n / size) * 100);
                    walkThroughPath(subDir.getName());

//                    if(last!=progress){
//                        System.out.println(progress+"%");
//                        last = progress;
//                    }
                }
//                n++;
            }
            return outputDir;
        }
        catch (Exception e){
            System.out.println("TextExtractor.readDirectories()");
            return outputDir;
        }
    }

    private void readDirectory(File subDir) {
        String courseDir = outputDir + sysSeparator + subDir.getName();
        ICorpusCreater iCorpusCreater = new CorpusCreater(courseDir);
        File[] files = subDir.listFiles();
        if (files != null){
            for (File file: files){
                FileInfo fileInfo = getFileInfo(file, subDir.getName());
                iCorpusCreater.add(file.getPath(), fileInfo);
            }
            iCorpusCreater.createCorpose();
        }

    }

    private FileInfo getFileInfo(File file, String courseName) {
        AReader fileReader = AReader.readerFactory(file.getName());
        String fileText = fileReader.readFile(file.getPath());
        FileInfo ans = new FileInfo(file.getName(), courseName, fileText);
        return ans;
    }
}
