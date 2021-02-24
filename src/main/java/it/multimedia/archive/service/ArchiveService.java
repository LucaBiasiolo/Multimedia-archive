package it.multimedia.archive.service;

import it.multimedia.archive.Archive;

import java.util.List;

public interface ArchiveService {

    List<Archive> getArchives();

    Archive getArchiveById(long archiveId);
}
