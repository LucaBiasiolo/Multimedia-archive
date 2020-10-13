import java.io.File;
import java.sql.*;
import java.util.Arrays;

public class NewFile extends File {

    public static final String[] ADMITTED_FILE_EXTENSIONS = {"jpg", "JPG", "jpeg", "png", "gif", "mp4", "opus", "mpeg", "mp3" };
    protected String fileExtension;
    protected int hashCode;
    protected int day;
    protected int month;
    protected int year;
    protected int progressiveNumber;

    public NewFile(String path) {
        super(path);
        this.fileExtension = this.getName().split("\\.")[1];
        this.hashCode = new File(this.getAbsolutePath()).hashCode();
    }

    public boolean checkFile() throws SQLException {
        boolean okay = true;
        if (!Arrays.asList(ADMITTED_FILE_EXTENSIONS).contains(this.fileExtension)){ // Se l'estensione del file non è tra quelle ammesse
            System.out.println("Impossibile processare " + this.getName() + " in quanto estensione non valida");
            okay = false;
        }
        else {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "select file_name from files where hash=%d",this.hashCode
            ));
            if(resultSet.next()){
                String fileName = resultSet.getString("File_name");
                System.out.println(this.getName() + " doppione di " + fileName);
                okay = false;
            }
            connection.close();
        }
        return okay;
    }

    public void renameAndMoveFile() throws SQLException {
        if (this.getName().split("\\.")[0].length() == 10) {
            this.renameTimestampFile();
        }
        else if (this.getName().startsWith("IMG-") || this.getName().startsWith("VID-")){
            this.renameIMGVIDFile();
        }
        else if (this.getName().startsWith("WP-")){
            this.renameWPFile();
        }
        else if (this.getName().split("\\.")[0].length() == 15) {
            this.renameYearMonthDayHourFile();
        }
        else {
            this.renameMDateFile();
        }
        Connection connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format(
                "select max (prog_number) from files where day =%s and month=%s and year=%s",this.day,this.month,this.year
        ));
        if (resultSet.next()){
            String maxProgressiveNumber = resultSet.getString("prog_number");
            if (maxProgressiveNumber.equals("NULL")){
                this.progressiveNumber = Integer.parseInt("1");
            }
            else{
                this.progressiveNumber = Integer.parseInt(maxProgressiveNumber) + 1;
            }
        }
        String newFileName = this.day + "-" + this.month + "-" + Integer.toString(this.year).substring(2) + "-" + this.progressiveNumber + "." + this.fileExtension;
        // TODO: implementare rinominazione file e spostamento nella cartella opportuna dell'archivio
        // TODO: implementare creazione cartella dell'anno e del mese se queste ancora non esistono
        Statement insertStatement = connection.createStatement();
        insertStatement.executeQuery(String.format(
                "insert into files values (%s,%s,%d,%d,%d,%d,%s,%s,%d)",
                "NULL", newFileName, this.day, this.month, this.year, this.progressiveNumber, this.fileExtension, this.getAbsolutePath(), this.hashCode
        ));
        connection.close();
    }

    private void renameYearMonthDayHourFile(){
        String[] pieces = this.getName().split("_");
        this.year = Integer.parseInt(pieces[0].substring(0,4));
        this.month = Integer.parseInt(pieces[0].substring(4,6).replaceFirst("^0+(?!$)", ""));
        this.day = Integer.parseInt(pieces[0].substring(6).replaceFirst("^0+(?!$)", ""));
    }

    private void renameTimestampFile(){
        Timestamp timestamp = new Timestamp(Integer.parseInt(this.getName().split("\\.")[0]));
        this.day = timestamp.getDay();
        this.month = timestamp.getMonth();
        this.year = timestamp.getYear();
    }

    private void renameIMGVIDFile(){
        String[] pieces = this.getName().split("-");
        this.year = Integer.parseInt(pieces[1].substring(0,4));
        this.month = Integer.parseInt(pieces[1].substring(4,6).replaceFirst("^0+(?!$)", ""));
        this.day = Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", ""));
    }

    private void renameWPFile(){
        String[] pieces = this.getName().split("_");
        this.year = Integer.parseInt(pieces[1].substring(0,4));
        this.month = Integer.parseInt(pieces[1].substring(4,6).replaceFirst("^0+(?!$)", ""));
        this.day = Integer.parseInt(pieces[1].substring(6).replaceFirst("^0+(?!$)", ""));
    }

    private void renameMDateFile(){
        Timestamp lastModifiedTimestamp = new Timestamp(new File(this.getAbsolutePath()).lastModified());
        this.day = lastModifiedTimestamp.getDay();
        this.month = lastModifiedTimestamp.getMonth();
        this.year = lastModifiedTimestamp.getYear();
    }
}
