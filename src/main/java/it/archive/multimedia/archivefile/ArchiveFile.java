package it.archive.multimedia.archivefile;

import javax.persistence.*;

@Entity
@Table(name = "anag_archive_files")
public class ArchiveFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private long id;

    @Column(name = "file_name", nullable = false, unique = true)
    private String name;

    @Column(name = "file_day")
    private int day;

    @Column(name = "file_month")
    private int month;

    @Column(name = "file_year")
    private int year;

    @Column(name = "file_prog_number", nullable = false)
    private int progNumber;

    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    @Column(name = "file_path", nullable = false, unique = true)
    private String path;

    @Column(name = "file_hash", nullable = false, unique = true)
    private String hash;

    public ArchiveFile() {
    }

    public ArchiveFile(String fileName) {
        this.day = Integer.parseInt(fileName.split("-")[0]);
        this.month = Integer.parseInt(fileName.split("-")[1]);
        this.year = Integer.parseInt(fileName.split("-")[2]);
        String finalPiece = fileName.split("-")[3];
        this.progNumber = Integer.parseInt(finalPiece.split("\\.")[0]);
        this.fileExtension = fileName.split("\\.")[1];
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getProgNumber() {
        return progNumber;
    }

    public void setProgNumber(int progNumber) {
        this.progNumber = progNumber;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
