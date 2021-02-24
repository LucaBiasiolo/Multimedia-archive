package it.archive.multimedia.archivefile.service;

import it.archive.multimedia.archivefile.ArchiveFile;
import it.archive.multimedia.exceptions.ProgressiveNumberNotFoundException;

import java.util.List;

public interface ArchiveFileService {

    List getArchiveFiles();

    ArchiveFile insertNewArchivefile(ArchiveFile archiveFile);

    int getMaxProgressiveNumberByDate(int day, int month, int year) throws ProgressiveNumberNotFoundException;
}
