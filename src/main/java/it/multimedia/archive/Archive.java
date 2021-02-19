package it.multimedia.archive;

import javax.persistence.*;

@Entity
@Table(name = "anag_archives")
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private long id;

    @Column(name = "archive_name", unique = true, nullable = false)
    private String name;

    @Column(name = "archive_path", unique = true, nullable = false)
    private String path;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}