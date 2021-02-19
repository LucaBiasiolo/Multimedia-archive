package it.multimedia.archive.file.service;

import it.multimedia.archive.exceptions.ProgressiveNumberNotFoundException;

import java.io.File;

public interface FileService {

    boolean checkFileExtension(File newFile);

    void renameFile(File newFile) throws ProgressiveNumberNotFoundException;
}
