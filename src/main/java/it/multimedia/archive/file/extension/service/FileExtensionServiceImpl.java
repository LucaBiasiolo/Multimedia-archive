package it.multimedia.archive.file.extension.service;

import it.multimedia.archive.file.extension.FileExtension;
import it.multimedia.archive.file.extension.repository.FileExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FileExtensionServiceImpl implements FileExtensionService {

    @Autowired
    private FileExtensionRepository fileExtensionRepository;

    @Transactional(readOnly = true)
    public List getFileExtensions() {
        return fileExtensionRepository.getFileExtensions();
    }

    // TODO: 18/02/2021 Sistemare logiche dei metodi aggiungendo comportamento in casi di errore
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public FileExtension insertFileExtension(FileExtension fileExtension) {
        return fileExtensionRepository.insertFileExtension(fileExtension);
    }

    // TODO: 18/02/2021 Sistemare logiche dei metodi aggiungendo comportamento in casi di errore
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public FileExtension modifyFileExtension(FileExtension updatedFileExtension) {
        return fileExtensionRepository.modifyFileExtension(updatedFileExtension);
    }

    // TODO: 18/02/2021 Sistemare logiche dei metodi aggiungendo comportamento in casi di errore
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public FileExtension deleteFileExtensionByName(String extensionName) {
        return fileExtensionRepository.deleteFileExtensionByName(extensionName);
    }
}