package it.archive.multimedia.file.extension.repository;

import it.archive.multimedia.file.extension.FileExtension;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    public FileExtension insertFileExtension(FileExtension fileExtension) {
        session().persist(fileExtension);
        return fileExtension;
    }

    public FileExtension modifyFileExtension(FileExtension updatedFileExtension) {
        FileExtension fileExtensionToUpdate = session().get(FileExtension.class, updatedFileExtension.getId());
        if (fileExtensionToUpdate != null) {
            fileExtensionToUpdate.setExtension(updatedFileExtension.getExtension());
            return fileExtensionToUpdate;
        } else {
            return null;
        }
    }

    public FileExtension deleteFileExtensionByName(String extensionName) {
        Query query = session().createQuery("from FileExtension where extension = :name");
        query.setParameter("name", extensionName);
        FileExtension extension = (FileExtension) query.uniqueResult();
        if (extension != null) {
            session().delete(extension);
        }
        return extension;
    }
}