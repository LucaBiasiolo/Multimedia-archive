package it.multimedia.archive.fileextension.repository;

import it.multimedia.archive.fileextension.FileExtension;

import java.util.List;

public interface FileExtensionRepository {

    List getFileExtensions();

    FileExtension insertFileExtension(FileExtension fileExtension);

    FileExtension modifyFileExtension(FileExtension updatedFileExtension);

    FileExtension deleteFileExtensionByName(String extensionName);
}
