package biasiolo.luca.multimediaarchive.archive;

import biasiolo.luca.multimediaarchive.MultimediaArchive;
import biasiolo.luca.multimediaarchive.newfile.NewFile;

import java.util.Properties;

public class ArchiveFile extends NewFile {

    private int day;
    private int month;
    private int year;
    private int progressiveNumber;
    private Properties properties = MultimediaArchive.properties;
    public static final String[] ADMITTED_FILE_EXTENSIONS = {"jpg", "JPG", "jpeg", "png", "gif", "mp4", "opus", "mpeg", "mp3"};

    public ArchiveFile(String path) {
        super(path);
        this.day = Integer.parseInt(this.getName().split("-")[0]);
        this.month = Integer.parseInt(this.getName().split("-")[1]);
        this.year = Integer.parseInt(this.getName().split("-")[2]);
        String finalPiece = this.getName().split("-")[3];
        this.progressiveNumber = Integer.parseInt(finalPiece.split("\\.")[0]);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getProgressiveNumber() {
        return progressiveNumber;
    }

    public void setProgressiveNumber(int progressiveNumber) {
        this.progressiveNumber = progressiveNumber;
    }
}
