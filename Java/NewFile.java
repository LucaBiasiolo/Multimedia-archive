import java.io.File;
import java.util.ArrayList;

public class NewFile {

    private String name;
    private String path;
    private String endOfFile;
    private String hash;

    private final String archivePath = "C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video-audio";
    private final ArrayList<String> endOfFiles = new ArrayList<>();
    public NewFile(String name, String path){
        // TODO: scrivere meglio aggiunta
        endOfFiles.add("jpg");
        endOfFiles.add("JPG");
        endOfFiles.add("jpeg");
        endOfFiles.add("png");
        endOfFiles.add("gif");
        endOfFiles.add("mp4");
        endOfFiles.add("opus");
        endOfFiles.add("mpeg");
        endOfFiles.add("mp3");
        this.name = name;
        this.path = path;
        this.endOfFile = this.name.split(".")[1];
        // TODO: this.hash = this.calculateHash();
    }

    protected void calculateHash(){
        File file = new File(this.path);
        // TODO: calcolare hash del file
        // return hash
    }

    public boolean checkFile(NewFile newFile){
        boolean okay = true;
        if (!endOfFiles.contains(this.endOfFile)){
            System.out.println("Impossibile processare " + this.name + "in quanto estensione non valida");
            okay = false;
        }
        else {
            // TODO: cercare possibili doppioni
        }
    }

    public void renameAndMoveFile(){
        //
    }

    private void renameYearMonthDayHourFile(){
        //
    }

    private void renameTimestampFile(){
        //
    }

    private void renameIMGVIDFile(){
        //
    }

    private void renameWPFile(){
        //
    }

    private void renameMDateFile(){
        //
    }
}
