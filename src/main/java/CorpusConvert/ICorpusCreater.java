package CorpusConvert;

public interface ICorpusCreater {
    /**
     * add a file to the corpus
     * @param fileName - file ID
     * @param mData - the content of the file
     */
    void add(String fileName, FileInfo mData);

    /**
     * creates a corpus file.
     */
    void createCorpose();
}
