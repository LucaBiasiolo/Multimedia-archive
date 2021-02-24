package it.archive.multimedia.web.controllers;

import it.archive.multimedia.Archive;
import it.archive.multimedia.service.ArchiveService;
import it.archive.multimedia.web.MultimediaArchiveAPIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    private final Logger logger = Logger.getLogger("it.archive.multimedia.web.controllers.ArchiveController");

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

    @PostMapping(value = "archives")
    private MultimediaArchiveAPIResponse<Archive> insertArchive(@RequestBody Archive newArchive) {
        MultimediaArchiveAPIResponse<Archive> response = new MultimediaArchiveAPIResponse<>();
        try {
            Archive addedArchive = archiveService.insertArchive(newArchive);
            response.setStatus("OK");
            response.setResponse(addedArchive);
        } catch (Exception exception) {
            logger.log(Level.SEVERE, exception.getMessage(), exception);
            response.setStatus("KO");
            List<String> messages = new ArrayList<>();
            messages.add(exception.getMessage());
            response.setMessages(messages);
        }
        return response;
    }
}