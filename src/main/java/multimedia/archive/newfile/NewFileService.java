package multimedia.archive.newfile;

import multimedia.archive.MultimediaArchive;
import multimedia.archive.database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class NewFileService {

    private final Properties properties = MultimediaArchive.properties;
    private final String[] ADMITTED_FILE_EXTENSIONS = properties.getProperty("ADMITTED_FILE_EXTENSIONS").split(",");
    private final Logger logger = Logger.getLogger("multimedia.archive.newfile.NewFileService");

    @Autowired
    private DatabaseService databaseService;

    private int day;
    private int month;
    private int year;

    public void processNewFile(NewFile newFile) {
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
        int progressiveNumber = databaseService.getProgNumber(this.day, this.month, this.year);
        String newFileName = this.day + "-" + this.month + "-" + Integer.toString(this.year).substring(2) + "-"
                + progressiveNumber + "." + newFile.fileExtension;
        newFile.renameTo(new File(properties.getProperty("NEW_FOLDER_PATH")+ newFileName));
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