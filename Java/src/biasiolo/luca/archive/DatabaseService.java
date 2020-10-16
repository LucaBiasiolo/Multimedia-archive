package biasiolo.luca.archive;

public class DatabaseService {
    // TODO: metodi di utility per il database

    private static DatabaseService databaseServiceInstance = null;

    private DatabaseService(){}

    public static DatabaseService getInstance() {
        if (databaseServiceInstance == null) {
            databaseServiceInstance = new DatabaseService();
        }
        return databaseServiceInstance;
    }
}