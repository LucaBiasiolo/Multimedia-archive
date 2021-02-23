package it.multimedia.archive.service;

import it.multimedia.archive.Archive;
import it.multimedia.archive.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Autowired
    private ArchiveRepository archiveRepository;

    @Transactional(readOnly = true)
    public List<Archive> getArchives() {
        return archiveRepository.getArchives();
    }
}