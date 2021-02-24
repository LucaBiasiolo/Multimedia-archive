package it.multimedia.archive.repository;

import it.multimedia.archive.Archive;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArchiveRepositoryImpl implements ArchiveRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List getArchives() {
        return session().createQuery("from Archive").list();
    }

    public Archive getArchiveById(long archiveId) {
        return session().get(Archive.class, archiveId);
    }

    public Archive insertArchive(Archive archive) {
        session().persist(archive);
        return archive;
    }
}