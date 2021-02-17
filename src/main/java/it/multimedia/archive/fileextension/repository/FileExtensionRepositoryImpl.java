package it.multimedia.archive.fileextension.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FileExtensionRepositoryImpl implements FileExtensionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List getFileExtensions() {
        return session().createQuery("from FileExtension").list();
    }
}