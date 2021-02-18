package it.multimedia.archive.fileextension.service;

import it.multimedia.archive.fileextension.FileExtension;

import java.util.List;

public interface FileExtensionService {

    List<FileExtension> getFileExtensions();

    FileExtension insertFileExtension(FileExtension fileExtension);

    FileExtension modifyFileExtension(FileExtension updatedFileExtension);

    FileExtension deleteFileExtensionByName(String extensionName);
}
