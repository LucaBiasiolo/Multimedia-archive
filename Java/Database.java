import java.sql.*;

public class Database {

    public Database() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
        Statement statement = connection.createStatement();
        statement.executeQuery("drop table Files");
        statement.executeQuery(
                "create table if not exists files (file_id integer primary key, file_name text unique, day integer, month integer, year integer, prog_number integer, eof text, path text unique, hash text unique)"
        );
        connection.close();
    }


}
