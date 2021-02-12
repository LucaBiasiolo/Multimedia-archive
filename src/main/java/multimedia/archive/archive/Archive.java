package multimedia.archive.archive;

import biasiolo.luca.multimediaarchive.MultimediaArchive;

import java.util.Properties;
import java.util.logging.Logger;

public class Archive {

    private static Archive archiveInstance = null;
    private final Logger logger = Logger.getLogger("Archive-logger");
    private final Properties properties = MultimediaArchive.properties;
    public String ARCHIVE_ROOT_PATH = properties.getProperty("ARCHIVE_ROOT_PATH");
    //TODO: implementare struttura archivio come albero di cartelle

    private Archive(){}

    public static Archive getInstance() {
        if (archiveInstance == null) {
            archiveInstance = new Archive();
        }
        return archiveInstance;
    }
}
