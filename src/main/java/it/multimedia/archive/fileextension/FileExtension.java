package it.multimedia.archive.fileextension;

import javax.persistence.*;

@Entity
@Table(name = "anag_file_extensions")
public class FileExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extension_id")
    private int id;

    @Column(name = "file_extension")
    private String extension;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}