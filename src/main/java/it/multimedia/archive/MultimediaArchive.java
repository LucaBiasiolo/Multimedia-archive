package it.multimedia.archive;

import java.util.Scanner;

public class MultimediaArchive {

    // TODO: implementare struttura archivio come albero di cartelle
    // TODO: rinominare opportunamente file in caso di modifiche all'archivio
    // TODO: utilizzare direttamente i path assoluti dei file invece di navigare nelle cartelle
    // TODO: controllare se esiste cartella con anno e mese. In caso negativo, crearle

    // TODO: Da spostare in classe opportuna

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Absolute path of the folder to add: ");
        final String NEW_FOLDER_PATH = scanner.nextLine();
        System.out.println("Absolute path of the folder in which to create the archive");
        final String ARCHIVE_ROOT_PATH = scanner.nextLine();
        scanner.close();
    }
}
