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
    // TODO: 24/02/2021 Implementare logiche
    public List<Archive> getArchives() {
        return archiveRepository.getArchives();
    }

    @Transactional(readOnly = true)
    // TODO: 24/02/2021 Implementare logiche
    public Archive getArchiveById(long archiveId) {
        return archiveRepository.getArchiveById(archiveId);
    }
}