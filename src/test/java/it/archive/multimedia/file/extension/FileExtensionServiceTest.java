package it.archive.multimedia.file.extension;

import it.archive.multimedia.file.extension.service.FileExtensionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"/application-context.xml"})
public class FileExtensionServiceTest {

    @Autowired
    private FileExtensionService fileExtensionService;

    @Test
    public void getFileExtensionsTest() {
        List<FileExtension> fileExtensions = fileExtensionService.getFileExtensions();
        Assertions.assertNotNull(fileExtensions);
        Assertions.assertEquals(9, fileExtensions.size());

        List<FileExtension> fileExtensionsFromDb = new ArrayList<>();
        FileExtension fileExtension1 = new FileExtension();
        fileExtension1.setId(1);
        fileExtension1.setExtension("jpg");
        fileExtensionsFromDb.add(fileExtension1);

        FileExtension fileExtension2 = new FileExtension();
        fileExtension2.setId(2);
        fileExtension2.setExtension("JPG");
        fileExtensionsFromDb.add(fileExtension2);

        FileExtension fileExtension3 = new FileExtension();
        fileExtension3.setId(3);
        fileExtension3.setExtension("jpeg");
        fileExtensionsFromDb.add(fileExtension3);

        FileExtension fileExtension4 = new FileExtension();
        fileExtension4.setId(4);
        fileExtension4.setExtension("png");
        fileExtensionsFromDb.add(fileExtension4);

        FileExtension fileExtension5 = new FileExtension();
        fileExtension5.setId(5);
        fileExtension5.setExtension("gif");
        fileExtensionsFromDb.add(fileExtension5);

        FileExtension fileExtension6 = new FileExtension();
        fileExtension6.setId(6);
        fileExtension6.setExtension("mp4");
        fileExtensionsFromDb.add(fileExtension6);

        FileExtension fileExtension7 = new FileExtension();
        fileExtension7.setId(7);
        fileExtension7.setExtension("opus");
        fileExtensionsFromDb.add(fileExtension7);

        FileExtension fileExtension8 = new FileExtension();
        fileExtension8.setId(8);
        fileExtension8.setExtension("mpeg");
        fileExtensionsFromDb.add(fileExtension8);

        FileExtension fileExtension9 = new FileExtension();
        fileExtension9.setId(9);
        fileExtension9.setExtension("mp3");
        fileExtensionsFromDb.add(fileExtension9);

        Assertions.assertArrayEquals(fileExtensionsFromDb.toArray(), fileExtensions.toArray());
    }
}