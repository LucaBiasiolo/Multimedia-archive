package it.multimedia.archive.archivefile;

import it.multimedia.archive.archivefile.service.ArchiveFileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class ArchiveFileServiceTest {

    @Autowired
    private ArchiveFileService archiveFileService;

    @Test
    public void getArchiveFilesTest() {
        List archiveFiles = archiveFileService.getArchiveFiles();
        assertNotNull(archiveFiles);
        assertEquals(0, archiveFiles.size());
    }
}