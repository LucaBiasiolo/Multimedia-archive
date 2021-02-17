package it.multimedia.archive.archivefile.service;

import it.multimedia.archive.archivefile.ArchiveFile;

import java.util.List;

public interface ArchiveFileService {

    List getArchiveFiles();

    ArchiveFile insertNewArchivefile(ArchiveFile archiveFile);

    int getMaxProgressiveNumberByDate(int day, int month, int year);
}
