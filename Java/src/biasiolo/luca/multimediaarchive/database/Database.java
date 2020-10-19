package biasiolo.luca.multimediaarchive.database;

public class Database {

    private static Database databaseInstance = null;

    private Database(){}

    public static Database getInstance() {
        if (databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }
}
