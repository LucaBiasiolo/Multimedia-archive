package biasiolo.luca.multimediaarchive.archive;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Archive {

    private static Archive archiveInstance = null;
    public static String ARCHIVE_ROOT_PATH;
    private final Logger logger = Logger.getLogger("Archive-logger");

    private Archive(){}

    public static Archive getInstance() {
        if (archiveInstance == null) {
            archiveInstance = new Archive();
        }
        return archiveInstance;
    }

    public void setArchiveRootPath(String path) {
        if (ARCHIVE_ROOT_PATH == null) {
            this.ARCHIVE_ROOT_PATH = path;
        }
        else{
            logger.log(Level.SEVERE, "Error: archive root path has already been set");
        }
    }
}
