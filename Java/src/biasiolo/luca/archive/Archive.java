package biasiolo.luca.archive;

public class Archive {

    public static String ARCHIVE_ROOT_PATH; //= "C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video-audio";
    public static final String[] ADMITTED_FILE_EXTENSIONS = {"jpg", "JPG", "jpeg", "png", "gif", "mp4", "opus", "mpeg", "mp3"};

    public void setArchiveRootPath(String path) {
        this.ARCHIVE_ROOT_PATH = path;
    }
}
