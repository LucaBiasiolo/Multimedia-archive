package biasiolo.luca.multimediaarchive.database;

import biasiolo.luca.multimediaarchive.MultimediaArchive;
import biasiolo.luca.multimediaarchive.archive.ArchiveFile;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseService {

    private static DatabaseService databaseServiceInstance = null;
    private final Logger logger = Logger.getLogger("Database-Service-logger");
    private final Properties properties = MultimediaArchive.properties;
    private DatabaseService(){}

    public static DatabaseService getInstance() {
        if (databaseServiceInstance == null) {
            databaseServiceInstance = new DatabaseService();
        }
        return databaseServiceInstance;
    }

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