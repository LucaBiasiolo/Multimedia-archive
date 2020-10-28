package biasiolo.luca.multimediaarchive.newfile;

import biasiolo.luca.multimediaarchive.MultimediaArchive;
import biasiolo.luca.multimediaarchive.database.DatabaseService;

import java.io.File;
import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.logging.Logger;

public class NewFileService {

    private static NewFileService newFileServiceInstance = null;
    private Properties properties = MultimediaArchive.properties;
    private String[] ADMITTED_FILE_EXTENSIONS = properties.getProperty("ADMITTED_FILE_EXTENSIONS").split(",");
    private final Logger logger = Logger.getLogger("biasiolo.luca.multimediaarchive.newfile.NewFileService");
    private final DatabaseService databaseService = DatabaseService.getInstance();
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

    public void processNewFile(NewFile newFile){
        checkFileExtension(newFile);
        renameFile(newFile);
        // TODO: implementare creazione cartelle dell'anno e del mese se queste ancora non esistono
        // TODO: rinominare, effettuare i controlli per lo spostamento, spostare e infine aggiungere dati al db
        // TODO: implementare rinominazione file e spostamento nella cartella opportuna dell'archivio
        // checkArchive()
        // moveFile(newFile);
        // addFileToDb()
    }

    public boolean checkFileExtension(NewFile newFile) {
        boolean okay = true;
        if (!Arrays.asList(ADMITTED_FILE_EXTENSIONS).contains(newFile.fileExtension)) {
            logger.info( newFile.getName() + " cannot be processed as its extension is invalid");
            okay = false;
        }
        return okay;
    }

    public void renameFile(NewFile newFile) {
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
        this.progressiveNumber = databaseService.getProgNumber(this.day,this.month,this.year);
        String newFileName = this.day + "-" + this.month + "-" + Integer.toString(this.year).substring(2) + "-"
                + this.progressiveNumber + "." + newFile.fileExtension;
        newFile.renameTo(new File(properties.getProperty("NEW_FOLDER_PATH")+ newFileName));
    }

    private void moveFile(NewFile newFile){
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