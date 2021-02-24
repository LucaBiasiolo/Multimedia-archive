package it.archive.multimedia.service;

import it.archive.multimedia.Archive;

import java.util.List;

public interface ArchiveService {

    List<Archive> getArchives();

    Archive getArchiveById(long archiveId);

    Archive insertArchive(Archive archive);
}
