package it.multimedia.archive.web.controllers;

import it.multimedia.archive.Archive;
import it.multimedia.archive.service.ArchiveService;
import it.multimedia.archive.web.MultimediaArchiveAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @GetMapping(value = "/archives")
    private MultimediaArchiveAPIResponse<List<Archive>> getArchives() {
        MultimediaArchiveAPIResponse<List<Archive>> response = new MultimediaArchiveAPIResponse<>();
        response.setStatus("OK");
        response.setResponse(archiveService.getArchives());
        return response;
    }

    @GetMapping(value = "/archives/{id}")
    private MultimediaArchiveAPIResponse<Archive> getArchiveById(@PathVariable(value = "id") long archiveId) {
        MultimediaArchiveAPIResponse<Archive> response = new MultimediaArchiveAPIResponse<>();
        Archive requestedArchive = archiveService.getArchiveById(archiveId);
        response.setStatus("OK");
        response.setResponse(requestedArchive);
        return response;
    }
}