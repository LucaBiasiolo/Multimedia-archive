package it.archive.multimedia.file.service;

import it.archive.multimedia.exceptions.ProgressiveNumberNotFoundException;

import java.io.File;

public interface FileService {

    boolean checkFileExtension(File newFile);

    void renameFile(File newFile) throws ProgressiveNumberNotFoundException;
}
