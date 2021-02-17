package it.multimedia.archive.archivefile.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArchiveFileRepositoryImpl implements ArchiveFileRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List getArchiveFiles() {
        return session().createQuery("from ArchiveFile").list();
    }

    public int getMaxProgressiveNumberByDate(int day, int month, int year) {
        // TODO: Da implementare
        return 0;
    }
}