package biasiolo.luca.archive;

public class ArchiveFile extends NewFile {

    protected int day;
    protected int month;
    protected int year;
    protected int progressiveNumber;

    public ArchiveFile(String path) {
        super(path);
        this.day = Integer.parseInt(this.getName().split("-")[0]);
        this.month = Integer.parseInt(this.getName().split("-")[1]);
        this.year = Integer.parseInt(this.getName().split("-")[2]);
        String finalPiece = this.getName().split("-")[3];
        this.progressiveNumber = Integer.parseInt(finalPiece.split("\\.")[0]);
    }
}
