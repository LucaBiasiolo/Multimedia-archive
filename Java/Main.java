import java.io.File;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Inserisci path assoluto della cartella da aggiungere: \n");
        Scanner scanner = new Scanner(System.in);
        String newFilesPath = scanner.nextLine();
        File directory = new File(newFilesPath);
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            NewFile newFile = new NewFile(file.getAbsolutePath());
            boolean okay = newFile.checkFile();
            if (okay) {
                newFile.renameAndMoveFile();
            }
        }
    }

    // TODO: implementare metodo che chieda all'utente dove creare l'archivio, con che nome
    // TODO: implementare GUI che permetta all'utente di eseguire le varie funzioni
}
