package it.archive.multimedia.file.extension.service;

import it.archive.multimedia.file.extension.FileExtension;

import java.util.List;

public interface FileExtensionService {

    List<FileExtension> getFileExtensions();

    FileExtension insertFileExtension(FileExtension fileExtension);

    FileExtension modifyFileExtension(FileExtension updatedFileExtension);

    FileExtension deleteFileExtensionByName(String extensionName);
}
