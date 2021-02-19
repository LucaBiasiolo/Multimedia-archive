package it.multimedia.archive.file;

import java.io.File;

public interface FileService {

    boolean checkFileExtension(File newFile);

    void renameFile(File newFile);
}
