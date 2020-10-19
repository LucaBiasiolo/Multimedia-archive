package biasiolo.luca.archive;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseService {

    private static DatabaseService databaseServiceInstance = null;
    private final Logger logger = Logger.getLogger("Database-Service-logger");
    private DatabaseService(){}

    public static DatabaseService getInstance() {
        if (databaseServiceInstance == null) {
            databaseServiceInstance = new DatabaseService();
        }
        return databaseServiceInstance;
    }

    public int getProgNumber(int day, int month, int year){
        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:archive.db")){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "select max (prog_number) from files where day =%d and month=%d and year=%d", day, month, year
            ));
            int maxProgressiveNumber = 0;
            if (resultSet.next()) {
                maxProgressiveNumber = resultSet.getInt("prog_number");
            }
            return maxProgressiveNumber;
        } catch (SQLException exception) {
            logger.log(Level.SEVERE, "An error occurred:"+ exception.getMessage());
            exception.printStackTrace();
            return 0;
        }
    }

    public void addFile(){

    }
}