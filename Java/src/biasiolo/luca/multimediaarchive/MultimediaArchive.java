package biasiolo.luca.multimediaarchive;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class MultimediaArchive {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Absolute path of the folder to add: ");
        final String NEW_FOLDER_PATH = scanner.nextLine();
        System.out.println("Absolute path of the folder in which to create the archive");
        final String ARCHIVE_ROOT_PATH = scanner.nextLine();
        scanner.close();
        Properties properties = new Properties();
        properties.setProperty("NEW_FOLDER_PATH", NEW_FOLDER_PATH);
        properties.setProperty("ARCHIVE_ROOT_PATH", ARCHIVE_ROOT_PATH);
        properties.store(new FileWriter("multimediaarchive.properties"),null);
    }
}
