package biasiolo.luca.multimediaarchive.archive;

public class ArchiveFileService {

    private static ArchiveFileService archiveFileServiceInstance = null;
    private ArchiveFileService(){}

    public static ArchiveFileService getInstance() {
        if (archiveFileServiceInstance == null) {
            archiveFileServiceInstance = new ArchiveFileService();
        }
        return archiveFileServiceInstance;
    }

    private void renameArchiveFiles(){
        // TODO: rinominare opportunamente file in caso di cancellazione
        // TODO: utilizzare direttamente i path assoluti dei file invece di navigare nelle cartelle
    }
}