import java.sql.*;
import java.util.ArrayList;

public class Archive {

    public static final String ARCHIVE_ROOT_PATH = "C:\\Users\\utente\\Desktop\\Luca\\Archivio foto-video-audio";

    private void renameArchiveFiles() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select path from files");
        ArrayList<ArchiveFile> archiveFiles = null;
        if (resultSet.next()) {
            String filePath = resultSet.getString("path");
            ArchiveFile archiveFile = new ArchiveFile(filePath);
            boolean okay = archiveFile.checkFile();
            if (okay) {
                // TODO
            }
        }
        // TODO: utilizzare direttamente i path assoluti dei file invece di navigare nelle cartelle
    }
}
