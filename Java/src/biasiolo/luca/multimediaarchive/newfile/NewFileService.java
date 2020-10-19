package biasiolo.luca.multimediaarchive.newfile;

import biasiolo.luca.multimediaarchive.archive.ArchiveFile;

import java.io.File;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewFileService {

    private static NewFileService newFileServiceInstance = null;
    private final Logger logger = Logger.getLogger("New-file-service-logger");
    private int day;
    private int month;
    private int year;
    private int progressiveNumber;
    private NewFileService() {}

    public static NewFileService getInstance() {
        if (newFileServiceInstance == null) {
            newFileServiceInstance = new NewFileService();
        }
        return newFileServiceInstance;
    }

    public boolean checkFileExtension(NewFile newFile) throws SQLException {
        boolean okay = true;
        if (!Arrays.asList(ArchiveFile.ADMITTED_FILE_EXTENSIONS).contains(newFile.fileExtension)) {
            logger.log(Level.INFO,"Impossibile processare " + newFile.getName() + " in quanto estensione non valida");
            okay = false;
        }
        return okay;
    }

    public void renameAndMoveFile(NewFile newFile) throws SQLException {
        int[] datePieces = new int[3];
        if (newFile.getName().split("\\.")[0].length() == 10) {
            renameTimestampFile(newFile);
        } else if (newFile.getName().startsWith("IMG-") || newFile.getName().startsWith("VID-")) {
            renameIMGVIDFile(newFile);
        } else if (newFile.getName().startsWith("WP-")) {
            renameWPFile(newFile);
        } else if (newFile.getName().split("\\.")[0].length() == 15) {
            renameYearMonthDayHourFile(newFile);
        } else {
            renameMDateFile(newFile);
        }
        // databaseService.getProgNumber(day,month,year);
        Connection connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format(
                "select max (prog_number) from files where day =%s and month=%s and year=%s", this.day, this.month, this.year
        ));
        if (resultSet.next()) {
            String maxProgressiveNumber = resultSet.getString("prog_number");
            if (maxProgressiveNumber.equals("NULL")) {
                this.progressiveNumber = Integer.parseInt("1");
            } else {
                this.progressiveNumber = Integer.parseInt(maxProgressiveNumber) + 1;
            }
        }
        String newFileName = this.day + "-" + this.month + "-" + Integer.toString(this.year).substring(2) + "-" + this.progressiveNumber + "." + newFile.fileExtension;
        // TODO: implementare rinominazione file e spostamento nella newFileella opportuna dell'archivio
        // TODO: implementare creazione newFileella dell'anno e del mese se queste ancora non esistono
        Statement insertStatement = connection.createStatement();
        insertStatement.executeQuery(String.format(
                "insert into files values (%s,%s,%d,%d,%d,%d,%s,%s,%d)",
                "NULL", newFileName, this.day, this.month, this.year, this.progressiveNumber, newFile.fileExtension, newFile.getAbsolutePath(), newFile.hashCode()
        ));
        connection.close();
    }

    private void renameYearMonthDayHourFile(NewFile newFile) {
        String[] pieces = newFile.getName().split("_");
        this.year = Integer.parseInt(pieces[0].substring(0, 4));
        this.month = Integer.parseInt(pieces[0].substring(4, 6).replaceFirst("^0+(?!$)", ""));
        this.day = Integer.parseInt(pieces[0].substring(6).replaceFirst("^0+(?!$)", ""));
    }

    private void renameTimestampFile(NewFile newFile) {
        Timestamp timestamp = new Timestamp(Integer.parseInt(newFile.getName().split("\\.")[0]));
        this.day = timestamp.getDay();
        this.month = timestamp.getMonth();
        this.year = timestamp.getYear();
    }

    private void renameIMGVIDFile(NewFile newFile) {
        String[] pieces = newFile.getName().split("-");
        this.year = Integer.parseInt(pieces[1].substring(0, 4));
        this.month = Integer.parseInt(pieces[1].substring(4, 6).replaceFirst("^0+(?!$)", ""));
        this.day = Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", ""));
    }

    private void renameWPFile(NewFile newFile) {
        String[] pieces = newFile.getName().split("_");
        this.year = Integer.parseInt(pieces[1].substring(0, 4));
        this.month = Integer.parseInt(pieces[1].substring(4, 6).replaceFirst("^0+(?!$)", ""));
        this.day = Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", ""));
    }

    private void renameMDateFile(NewFile newFile) {
        Timestamp lastModifiedTimestamp = new Timestamp(new File(newFile.getAbsolutePath()).lastModified());
        this.day = lastModifiedTimestamp.getDay();
        this.month = lastModifiedTimestamp.getMonth();
        this.year = lastModifiedTimestamp.getYear();
    }
}