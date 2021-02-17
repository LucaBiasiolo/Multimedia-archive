package it.multimedia.archive.archivefile.service;

import java.util.List;

public interface ArchiveFileService {

    List getArchiveFiles();

    int getMaxProgressiveNumberByDate(int day, int month, int year);
}
