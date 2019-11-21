package CorpusConvert;

public class FileInfo {

    String fileName;
    String txt;
    String course;
    String lecturer;

    public FileInfo(String fileName, String course, String txt) {
        this.fileName = fileName;
        this.txt = txt;
        this.course = course;
    }



    public String getFileName() {
        return fileName;
    }

    public String getTxt() {
        return txt;
    }

    public String getCourse() {
        return course;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}

