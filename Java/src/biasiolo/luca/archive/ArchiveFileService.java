package biasiolo.luca.archive;

import java.sql.*;
import java.util.ArrayList;

public class ArchiveFileService {

    private static ArchiveFileService archiveFileServiceInstance = null;

    private ArchiveFileService(){}

    public static ArchiveFileService getInstance() {
        if (archiveFileServiceInstance == null) {
            archiveFileServiceInstance = new ArchiveFileService();
        }
        return archiveFileServiceInstance;
    }

    private void renameArchiveFiles() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:archive.db");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select path from files");
        ArrayList<ArchiveFile> archiveFiles = null;
        if (resultSet.next()) {
            String filePath = resultSet.getString("path");
            ArchiveFile archiveFile = new ArchiveFile(filePath);
            boolean okay = NewFileService.checkFile(archiveFile);
            if (okay) {
                // TODO
            }
        }
        // TODO: utilizzare direttamente i path assoluti dei file invece di navigare nelle cartelle
    }

//        public void rename() throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(String.format(
//                "select max (prog_number) from files where day =%s and month=%s and year=%s",this.day,this.month,this.year
//        ));
//        if (resultSet.next()){
//            String maxProgressiveNumber = resultSet.getString("prog_number");
//            if (maxProgressiveNumber.equals("NULL")) {
//                this.progressiveNumber = Integer.parseInt(maxProgressiveNumber) + 1;
//            } else {
//                this.progressiveNumber = Integer.parseInt("1");
//            }
//        }
//        String archiveFileName = this.day + "-" + this.month + "-" + Integer.toString(this.year).substring(2) + "-" + this.progressiveNumber + "-" + this.fileExtension;
//        // TODO: implementare interazione con il sistema operativo
    //}
}