package it.archive.multimedia.archivefile.repository;

import it.archive.multimedia.archivefile.ArchiveFile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public ArchiveFile insertNewArchiveFile(ArchiveFile archiveFile) {
        session().persist(archiveFile);
        return archiveFile;
    }

    public int getMaxProgressiveNumberByDate(int day, int month, int year) {
        Query query = session().createQuery("select progNumber from ArchiveFile where day = :day" +
                " and month = :month and year = :year");
        query.setParameter("day", day);
        query.setParameter("month", month);
        query.setParameter("year", year);
        return (Integer) query.uniqueResult();
    }
}