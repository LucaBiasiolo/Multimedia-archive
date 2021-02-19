package it.multimedia.archive.file.extension.repository;

import it.multimedia.archive.file.extension.FileExtension;

import java.util.List;

public interface FileExtensionRepository {

    List getFileExtensions();

    FileExtension insertFileExtension(FileExtension fileExtension);

    FileExtension modifyFileExtension(FileExtension updatedFileExtension);

    FileExtension deleteFileExtensionByName(String extensionName);
}
