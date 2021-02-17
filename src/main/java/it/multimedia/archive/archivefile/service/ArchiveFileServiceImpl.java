package it.multimedia.archive.archivefile.service;

import it.multimedia.archive.archivefile.ArchiveFile;
import it.multimedia.archive.archivefile.repository.ArchiveFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArchiveFileServiceImpl implements ArchiveFileService {

    @Autowired
    private ArchiveFileRepository archiveFileRepository;

    @Transactional(readOnly = true)
    public List getArchiveFiles() {
        return archiveFileRepository.getArchiveFiles();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ArchiveFile insertNewArchivefile(ArchiveFile archiveFile) {
        return archiveFileRepository.insertNewArchiveFile(archiveFile);
    }

    @Transactional(readOnly = true)
    public int getMaxProgressiveNumberByDate(int day, int month, int year) {
        return archiveFileRepository.getMaxProgressiveNumberByDate(day, month, year);
    }
}