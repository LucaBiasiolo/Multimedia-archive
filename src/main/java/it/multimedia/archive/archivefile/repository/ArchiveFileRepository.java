package it.multimedia.archive.archivefile.repository;

import java.util.List;

public interface ArchiveFileRepository {

    List getArchiveFiles();

    int getMaxProgressiveNumberByDate(int day, int month, int year);
}
