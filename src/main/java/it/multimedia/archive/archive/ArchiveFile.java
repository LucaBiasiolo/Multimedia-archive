package it.multimedia.archive.archive;

import it.multimedia.archive.newfile.NewFile;

public class ArchiveFile extends NewFile {

    private int day;
    private int month;
    private int year;
    private int progressiveNumber;

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
