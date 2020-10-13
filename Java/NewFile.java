import java.io.File;
import java.sql.*;
import java.util.Arrays;

public class NewFile {

    private String name;
    private String path;
    private String endOfFile;
    private int hashCode;
    private String day;
    private String month;
    private String year;
    private final String archivePath = "C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video-audio";
    private final String[] endOfFiles = {"jpg", "JPG", "jpeg", "png", "gif", "mp4", "opus", "mpeg", "mp3" };

    public NewFile(String name, String path){
        this.name = name;
        this.path = path;
        this.endOfFile = this.name.split(".")[1];
        this.hashCode = new File(this.path).hashCode();
    }

    public boolean checkFile(NewFile newFile) throws SQLException {
        boolean okay = true;
        if (Arrays.asList(endOfFiles).contains(this.endOfFile)){
            System.out.println("Impossibile processare " + this.name + "in quanto estensione non valida");
            okay = false;
        }
        else {
            // TODO: cercare possibili doppioni
            // Usare java.sql -> Classe DriverManager -> metodo getConnection()
//            Connection connection  = DriverManager.getConnection("jdbc:sqlite:archive.db");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from Files");

        }

    }

    public void renameAndMoveFile(){
        if (this.name.split(".")[0].length() == 10) {
            this.renameTimestampFile();
        }
        else if (this.name.startsWith("IMG-") || this.name.startsWith("VID-")){
            this.renameIMGVIDFile();
        }
        else if (this.name.startsWith("WP-")){
            this.renameWPFile();
        }
        else if (this.name.split(".")[0].length() == 15) {
            this.renameYearMonthDayHourFile();
        }
        else {
            this.renameMDateFile();
        }
        //

    }

    private void renameYearMonthDayHourFile(){
        String[] pieces = this.name.split("_");
        this.year = pieces[0].substring(0,4);
        this.month = pieces[0].substring(4,6).replaceFirst("^0+(?!$)", "");
        this.day = pieces[0].substring(6).replaceFirst("^0+(?!$)", "");
    }

    private void renameTimestampFile(){
        //
    }

    private void renameIMGVIDFile(){
        String[] pieces = this.name.split("-");
        this.year = pieces[1].substring(0,4);
        this.month = pieces[1].substring(4,6).replaceFirst("^0+(?!$)", "");
        this.day = pieces[1].substring(6).replaceFirst("^0+(?!$)", "");
    }

    private void renameWPFile(){
        String[] pieces = this.name.split("_");
        this.year = pieces[1].substring(0,4);
        this.month = pieces[1].substring(4,6).replaceFirst("^0+(?!$)", "");
        this.day = pieces[1].substring(6).replaceFirst("^0+(?!$)", "");
    }

    private void renameMDateFile(){
        //
    }
}
