package it.multimedia.archive.newfile;

import it.multimedia.archive.MultimediaArchive;
import it.multimedia.archive.database.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

@Service
public class NewFileService {

    private final Properties properties = MultimediaArchive.properties;
    private final String[] ADMITTED_FILE_EXTENSIONS = properties.getProperty("ADMITTED_FILE_EXTENSIONS").split(",");
    private final Logger logger = Logger.getLogger("multimedia.archive.newfile.NewFileService");

    @Autowired
    private DatabaseService databaseService;

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
            logger.info(newFile.getName() + " cannot be processed as its extension is invalid");
            okay = false;
        }
        return okay;
    }

    public void renameFile(NewFile newFile) {
        Map<String, Integer> newFileData;
        if (newFile.getName().split("\\.")[0].length() == 10) {
            newFileData = renameTimestampFile(newFile);
        } else if (newFile.getName().startsWith("IMG-") || newFile.getName().startsWith("VID-")) {
            newFileData = renameIMGVIDFile(newFile);
        } else if (newFile.getName().startsWith("WP-")) {
            newFileData = renameWPFile(newFile);
        } else if (newFile.getName().split("\\.")[0].length() == 15) {
            newFileData = renameYearMonthDayHourFile(newFile);
        } else {
            newFileData = renameMDateFile(newFile);
        }
        int progressiveNumber = databaseService.getProgNumber(newFileData.get("day"), newFileData.get("month"), newFileData.get("year"));
        String newFileName = String.format("%d-%d-%s-%d.%s", newFileData.get("day"), newFileData.get("month"),
                Integer.toString(newFileData.get("year")).substring(2), progressiveNumber, newFile.fileExtension);
        newFile.renameTo(new File(properties.getProperty("NEW_FOLDER_PATH") + newFileName));
    }

    private Map renameYearMonthDayHourFile(NewFile newFile) {
        List<String> pieces = Arrays.asList(newFile.getName().split("_"));
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", Integer.parseInt(pieces.get(0).substring(0, 4)));
        newFileData.put("month", Integer.parseInt(pieces.get(0).substring(4, 6).replaceFirst("^0+(?!$)", "")));
        newFileData.put("day", Integer.parseInt(pieces.get(0).substring(6).replaceFirst("^0+(?!$)", "")));
        return newFileData;
    }

    private Map renameTimestampFile(NewFile newFile) {
        Timestamp timestamp = new Timestamp(Integer.parseInt(newFile.getName().split("\\.")[0]));
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", timestamp.getYear());
        newFileData.put("month", timestamp.getMonth());
        newFileData.put("day", timestamp.getDay());
        return newFileData;
    }

    private Map renameIMGVIDFile(NewFile newFile) {
        String[] pieces = newFile.getName().split("-");
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", Integer.parseInt(pieces[1].substring(0, 4)));
        newFileData.put("month", Integer.parseInt(pieces[1].substring(4, 6).replaceFirst("^0+(?!$)", "")));
        newFileData.put("day", Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", "")));
        return newFileData;
    }

    private Map renameWPFile(NewFile newFile) {
        String[] pieces = newFile.getName().split("_");
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", Integer.parseInt(pieces[1].substring(0, 4)));
        newFileData.put("month", Integer.parseInt(pieces[1].substring(4, 6).replaceFirst("^0+(?!$)", "")));
        newFileData.put("day", Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", "")));
        return newFileData;
    }

    private Map renameMDateFile(NewFile newFile) {
        Timestamp lastModifiedTimestamp = new Timestamp(new File(newFile.getAbsolutePath()).lastModified());
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", lastModifiedTimestamp.getYear());
        newFileData.put("month", lastModifiedTimestamp.getMonth());
        newFileData.put("day", lastModifiedTimestamp.getDay());
        return newFileData;
    }
}