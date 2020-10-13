import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArchiveFile extends NewFile{

    public ArchiveFile(String name, String path) throws SQLException {
        super(name, path);
        this.day = this.name.split("-")[0];
        this.month = this.name.split("-")[1];
        this.year = this.name.split("-")[2];
        String finalPiece = this.name.split("-")[3];
        this.progressiveNumber = Integer.parseInt(finalPiece.split(".")[0]);
    }

    public void rename() throws SQLException {
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
}
