package it.archive.multimedia.archivefile.repository;

import it.archive.multimedia.archivefile.ArchiveFile;

import java.util.List;

public interface ArchiveFileRepository {

    List getArchiveFiles();

    ArchiveFile insertNewArchiveFile(ArchiveFile archiveFile);

    int getMaxProgressiveNumberByDate(int day, int month, int year);
}
