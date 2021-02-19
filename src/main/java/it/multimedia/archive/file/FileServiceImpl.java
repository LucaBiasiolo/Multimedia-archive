package it.multimedia.archive.file;

import it.multimedia.archive.archivefile.service.ArchiveFileService;
import it.multimedia.archive.fileextension.FileExtension;
import it.multimedia.archive.fileextension.service.FileExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileExtensionService fileExtensionService;

    @Autowired
    private ArchiveFileService archiveFileService;

    private final Logger logger = Logger.getLogger("it.multimedia.archive.newfile.NewFileService");

    public void processNewFile(File newFile) {
        checkFileExtension(newFile);
        renameFile(newFile);
        // TODO: implementare creazione cartelle dell'anno e del mese se queste ancora non esistono
        // TODO: rinominare, effettuare i controlli per lo spostamento, spostare e infine aggiungere dati al db
        // TODO: implementare rinominazione file e spostamento nella cartella opportuna dell'archivio
        // checkArchive()
        // moveFile(newFile);
        // addFileToDb()
    }

    public boolean checkFileExtension(File newFile) {
        List<FileExtension> extensionsObjects = fileExtensionService.getFileExtensions();
        List<String> extensions = extensionsObjects.stream().map(FileExtension::getExtension).collect(Collectors.toList());
        String newFileExtension = newFile.getName().split("\\.")[1];
        if (!extensions.contains(newFileExtension)) {
            logger.warning("Il file " + newFile + " non può essere processato in quanto la sua estensione è invalida");
            return false;
        }
        return true;
    }

    public void renameFile(File newFile) {
        Map<String, Integer> newFileData;
        String newFileExtension = newFile.getName().split("\\.")[1];
        if (newFile.getName().split("\\.")[0].length() == 10) {
            newFileData = renameTimestampFile(newFile);
        } else if (newFile.getName().startsWith("IMG-") || newFile.getName().startsWith("VID-")) {
            newFileData = renameIMGVIDFile(newFile);
        } else if (newFile.getName().startsWith("WP-")) {
            newFileData = renameWPFile(newFile);
        } else if (newFile.getName().split("\\.")[0].length() == 15) {
            newFileData = renameYearMonthDayHourFile(newFile);
        } else {
            newFileData = renameLastModifiedDateFile(newFile);
        }
        int progressiveNumber = archiveFileService.getMaxProgressiveNumberByDate(newFileData.get("day"), newFileData.get("month"), newFileData.get("year"));
        String newFileName = String.format("%d-%d-%s-%d.%s", newFileData.get("day"), newFileData.get("month"),
                Integer.toString(newFileData.get("year")).substring(2), progressiveNumber, newFileExtension);
//        Files.move(newFile.getAbsolutePath(), )// TODO: 19/02/2021 Inserire path cartella in cui spostare il file
    }

    private Map renameYearMonthDayHourFile(File newFile) {
        List<String> pieces = Arrays.asList(newFile.getName().split("_"));
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", Integer.parseInt(pieces.get(0).substring(0, 4)));
        newFileData.put("month", Integer.parseInt(pieces.get(0).substring(4, 6).replaceFirst("^0+(?!$)", "")));
        newFileData.put("day", Integer.parseInt(pieces.get(0).substring(6).replaceFirst("^0+(?!$)", "")));
        return newFileData;
    }

    private Map renameTimestampFile(File newFile) {
        Date timestamp = new Date(Integer.parseInt(newFile.getName().split("\\.")[0]));
        Map<String, Integer> newFileData = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        newFileData.put("year", calendar.get(Calendar.YEAR));
        newFileData.put("month", calendar.get(Calendar.MONTH));
        newFileData.put("day", calendar.get(Calendar.DAY_OF_WEEK));
        return newFileData;
    }

    private Map renameIMGVIDFile(File newFile) {
        String[] pieces = newFile.getName().split("-");
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", Integer.parseInt(pieces[1].substring(0, 4)));
        newFileData.put("month", Integer.parseInt(pieces[1].substring(4, 6).replaceFirst("^0+(?!$)", "")));
        newFileData.put("day", Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", "")));
        return newFileData;
    }

    private Map renameWPFile(File newFile) {
        String[] pieces = newFile.getName().split("_");
        Map<String, Integer> newFileData = new HashMap<>();
        newFileData.put("year", Integer.parseInt(pieces[1].substring(0, 4)));
        newFileData.put("month", Integer.parseInt(pieces[1].substring(4, 6).replaceFirst("^0+(?!$)", "")));
        newFileData.put("day", Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", "")));
        return newFileData;
    }

    private Map renameLastModifiedDateFile(File newFile) {
        Date lastModifiedTimestamp = new Date(new File(newFile.getAbsolutePath()).lastModified());
        Map<String, Integer> newFileData = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastModifiedTimestamp);
        newFileData.put("year", calendar.get(Calendar.YEAR));
        newFileData.put("month", calendar.get(Calendar.MONTH));
        newFileData.put("day", calendar.get(Calendar.DAY_OF_WEEK));
        return newFileData;
    }
}