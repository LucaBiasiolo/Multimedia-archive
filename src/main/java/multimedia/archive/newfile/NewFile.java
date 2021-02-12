package multimedia.archive.newfile;

import java.io.File;

public class NewFile extends File {

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    protected String fileExtension;

    public NewFile(String path) {
        super(path);
        this.fileExtension = this.getName().split("\\.")[1];
    }
}
