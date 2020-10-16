package biasiolo.luca.archive;

public class Archive {

    public static String ARCHIVE_ROOT_PATH;

    public void setArchiveRootPath(String path) {
        if (ARCHIVE_ROOT_PATH == null) {
            this.ARCHIVE_ROOT_PATH = path;
        }
        else{
            // TODO: da trasformare in log di errore
            System.out.println("Errore: root path dell'archivio già impostato");
        }
    }
}
