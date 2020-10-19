package biasiolo.luca.multimediaarchive.newfile;

import java.io.File;

public class NewFile extends File {

    protected String fileExtension;

    public NewFile(String path) {
        super(path);
        this.fileExtension = this.getName().split("\\.")[1];
    }
}
