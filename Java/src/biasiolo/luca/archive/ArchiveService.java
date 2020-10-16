package biasiolo.luca.archive;

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
}