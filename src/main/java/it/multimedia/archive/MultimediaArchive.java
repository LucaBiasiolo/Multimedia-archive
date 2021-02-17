package it.multimedia.archive;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class MultimediaArchive {

    public static final Properties properties = new Properties();

    // TODO: metodi di utility per gestire l'archivio multimediale
    // TODO: implementare struttura archivio come albero di cartelle
    // TODO: rinominare opportunamente file in caso di cancellazione
    // TODO: utilizzare direttamente i path assoluti dei file invece di navigare nelle cartelle
    // TODO: controllare se esiste cartella con anno e mese. In caso negativo, crearle

    // TODO: Da spostare in classe opportuna

    /*    public ArchiveFile(String path) {
        this.day = Integer.parseInt(this.getName().split("-")[0]);
        this.month = Integer.parseInt(this.getName().split("-")[1]);
        this.year = Integer.parseInt(this.getName().split("-")[2]);
        String finalPiece = this.getName().split("-")[3];
        this.progNumber = Integer.parseInt(finalPiece.split("\\.")[0]);
    }*/

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Absolute path of the folder to add: ");
        final String NEW_FOLDER_PATH = scanner.nextLine();
        System.out.println("Absolute path of the folder in which to create the archive");
        final String ARCHIVE_ROOT_PATH = scanner.nextLine();
        scanner.close();
    }
}
