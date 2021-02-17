package it.multimedia.archive.fileextension.service;

import it.multimedia.archive.fileextension.repository.FileExtensionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}