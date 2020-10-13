import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private final Connection connection;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
        Statement statement = connection.createStatement();
        statement.executeQuery("drop table Files");
        statement.executeQuery("CREATE TABLE IF NOT EXISTS Files (File_id INTEGER PRIMARY KEY, File_name TEXT UNIQUE, Day INTEGER, Month INTEGER, Year INTEGER, Prog_number INTEGER, eof TEXT,hash TEXT UNIQUE)");
        // TODO: implementare interazione con il sistema operativo
        connection.close();
    }
}
