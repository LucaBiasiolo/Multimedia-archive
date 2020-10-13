import java.io.File;
import java.sql.*;
import java.util.Arrays;

public class NewFile {

    public static final String ARCHIVE_PATH = "C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video-audio";
    public static final String[] END_OF_FILES = {"jpg", "JPG", "jpeg", "png", "gif", "mp4", "opus", "mpeg", "mp3" };

    protected String name;
    protected String path;
    protected String endOfFile;
    protected int hashCode;
    protected String day;
    protected String month;
    protected String year;
    protected int progressiveNumber;
    protected Connection connection;

    public NewFile(String name, String path) throws SQLException {
        this.name = name;
        this.path = path;
        this.endOfFile = this.name.split(".")[1];
        this.hashCode = new File(this.path).hashCode();
        this.connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
    }

    public boolean checkFile(NewFile newFile) throws SQLException {
        boolean okay = true;
        if (Arrays.asList(END_OF_FILES).contains(this.endOfFile)){
            System.out.println("Impossibile processare " + this.name + "in quanto estensione non valida");
            okay = false;
        }
        else {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("select File_name from Files where hash=%d",this.hashCode));
            if(resultSet.next()){
                String fileName = resultSet.getString("File_name");
                System.out.println(this.name + " doppione di " + fileName);
                okay = false;
            }
            connection.close();
        }
        return okay;
    }

    public void renameAndMoveFile() throws SQLException {
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
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format(
                "select max (Prog_number) from Files where Day =%s and Month=%s and Year=%s",this.day,this.month,this.year
        ));
        if (resultSet.next()){
            String maxProgressiveNumber = resultSet.getString("Prog_number");
            if (maxProgressiveNumber == "NULL"){
                this.progressiveNumber = Integer.parseInt("1");
            }
            else{
                this.progressiveNumber = Integer.parseInt(maxProgressiveNumber) + 1;
            }
        }
        this.name = this.day + "-" + this.month + "-" + this.year.substring(2) + "-" + this.progressiveNumber + "-" + this.endOfFile;
        // TODO: implementare interazione con il sistema operativo
    }

    private void renameYearMonthDayHourFile(){
        String[] pieces = this.name.split("_");
        this.year = pieces[0].substring(0,4);
        this.month = pieces[0].substring(4,6).replaceFirst("^0+(?!$)", "");
        this.day = pieces[0].substring(6).replaceFirst("^0+(?!$)", "");
    }

    private void renameTimestampFile(){
        // TODO: capire come convertire un timestamp in data
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
        // TODO: capire come convertire un timestamp in data
    }
}
