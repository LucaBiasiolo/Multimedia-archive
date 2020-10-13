import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArchiveFile extends NewFile{

    private Connection connection;

    public ArchiveFile(String path){
        super(path);
        this.day = Integer.parseInt(this.getName().split("-")[0]);
        this.month = Integer.parseInt(this.getName().split("-")[1]);
        this.year = Integer.parseInt(this.getName().split("-")[2]);
        String finalPiece = this.getName().split("-")[3];
        this.progressiveNumber = Integer.parseInt(finalPiece.split("\\.")[0]);
    }

    public void rename() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format(
                "select max (prog_number) from files where day =%s and month=%s and year=%s",this.day,this.month,this.year
        ));
        if (resultSet.next()){
            String maxProgressiveNumber = resultSet.getString("prog_number");
            if (maxProgressiveNumber.equals("NULL")) {
                this.progressiveNumber = Integer.parseInt(maxProgressiveNumber) + 1;
            } else {
                this.progressiveNumber = Integer.parseInt("1");
            }
        }
        String newFileName = this.day + "-" + this.month + "-" + Integer.toString(this.year).substring(2) + "-" + this.progressiveNumber + "-" + this.fileExtension;
        // TODO: implementare interazione con il sistema operativo
    }
}
