package biasiolo.luca.multimediaarchive.archive;

import biasiolo.luca.multimediaarchive.newfile.NewFile;

public class ArchiveService {
    // TODO: metodi di utility per gestire l'archivio multimediale

    private static ArchiveService archiveServiceInstance = null;

    private ArchiveService(){}

    public static ArchiveService getInstance() {
        if (archiveServiceInstance == null) {
            archiveServiceInstance = new ArchiveService();
        }
        return archiveServiceInstance;
    }

    public void checkArchive(NewFile newFile){
        // TODO: controllare se esiste cartella con anno e mese. In caso negativo, crearle
    }
}