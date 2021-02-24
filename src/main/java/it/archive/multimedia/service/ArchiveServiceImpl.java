package it.archive.multimedia.service;

import it.archive.multimedia.Archive;
import it.archive.multimedia.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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

    @Transactional(isolation = Isolation.READ_COMMITTED)
    // TODO: 24/02/2021 Implementare logiche
    public Archive insertArchive(Archive archive) {
        return archiveRepository.insertArchive(archive);
    }
}