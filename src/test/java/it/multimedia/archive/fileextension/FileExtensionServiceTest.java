package it.multimedia.archive.fileextension;

import it.multimedia.archive.fileextension.service.FileExtensionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class FileExtensionServiceTest {

    @Autowired
    private FileExtensionService fileExtensionService;

    @Test
    public void getFileExtensionsTest() {
        List fileExtensions = fileExtensionService.getFileExtensions();
        Assertions.assertNotNull(fileExtensions);
        Assertions.assertEquals(9, fileExtensions.size());
        // TODO: Aggiungere test esplicito con array creato a mano
    }
}