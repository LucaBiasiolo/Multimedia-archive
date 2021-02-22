package it.multimedia.archive.file.extension.service;

import it.multimedia.archive.file.extension.FileExtension;

import java.util.List;

public interface FileExtensionService {

    List<FileExtension> getFileExtensions();

    FileExtension insertFileExtension(FileExtension fileExtension);

    FileExtension modifyFileExtension(FileExtension updatedFileExtension);

    FileExtension deleteFileExtensionByName(String extensionName);
}
