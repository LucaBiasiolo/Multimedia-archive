package it.multimedia.archive.archivefile.repository;

import it.multimedia.archive.archivefile.ArchiveFile;

import java.util.List;

public interface ArchiveFileRepository {

    List getArchiveFiles();

    ArchiveFile insertNewArchiveFile(ArchiveFile archiveFile);

    int getMaxProgressiveNumberByDate(int day, int month, int year);
}
