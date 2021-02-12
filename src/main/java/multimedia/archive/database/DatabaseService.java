package multimedia.archive.database;

import multimedia.archive.MultimediaArchive;
import multimedia.archive.archive.ArchiveFile;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class DatabaseService {

    private final Logger logger = Logger.getLogger("multimedia.archive.database.DatabaseService");
    private final Properties properties = MultimediaArchive.properties;

    public int getProgNumber(int day, int month, int year) {
        int maxProgressiveNumber = 1;
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db_url"))) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "select max (prog_number) from files where day =%d and month=%d and year=%d", day, month, year
            ));
            if (resultSet.next()) {
                maxProgressiveNumber = resultSet.getInt("prog_number");
            }
        } catch (SQLException exception) {
            logger.severe("An error occurred:" + exception.getMessage());
            exception.printStackTrace();
        }
        if (maxProgressiveNumber != 1) {
            return ++maxProgressiveNumber;
        } else {
            return 1;
        }
    }

    // TODO: Da spostare in classe DAO
    public void addFileToDb(ArchiveFile archiveFileToAdd){
        try (Connection connection = DriverManager.getConnection(properties.getProperty("db_url"))) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format( "insert into files values (%s,%s,%d,%d,%d,%d,%s,%s,%d)",
                    "NULL", archiveFileToAdd.getName(), archiveFileToAdd.getDay(), archiveFileToAdd.getMonth(), archiveFileToAdd.getYear(),
                    archiveFileToAdd.getProgressiveNumber(), archiveFileToAdd.getFileExtension(), archiveFileToAdd.getAbsolutePath(),
                    archiveFileToAdd.hashCode()
            ));
        } catch (SQLException exception) {
            logger.severe("An error occurred:" + exception.getMessage());
            exception.printStackTrace();
        }
    }
}