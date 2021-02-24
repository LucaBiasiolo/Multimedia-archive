package it.archive.multimedia.file.extension;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "anag_file_extensions")
public class FileExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extension_id")
    private int id;

    @Column(name = "file_extension", unique = true, nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileExtension)) return false;
        FileExtension that = (FileExtension) o;
        return extension.equals(that.extension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(extension);
    }
}