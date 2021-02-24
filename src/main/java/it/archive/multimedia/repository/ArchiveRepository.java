package it.archive.multimedia.repository;

import it.archive.multimedia.Archive;

import java.util.List;

public interface ArchiveRepository {

    List<Archive> getArchives();

    Archive insertArchive(Archive archive);

    Archive getArchiveById(long archiveId);
}
