/*
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang, Siva. All rights reserved.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.ApartmentPhoto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ApartmentPhotoFacade extends AbstractFacade<ApartmentPhoto> {
    /*
    ---------------------------------------------------------------------------------------------
    Annotating 'private EntityManagerfindPhotosByApartmentPrimaryKey em;' with '@PersistenceContext(unitName = "FlatmatesPU")'
    implies that the EntityManager instance pointed to by 'em' is associated with the 'FlatmatesPU'
    persistence context. The persistence context is a set of entity (ApartmentPhoto) instances in which for
    any persistent entity (ApartmentPhoto) identity, there is a unique entity (ApartmentPhoto) instance.
    Within the persistence context, the entity (ApartmentPhoto) instances and their life cycle are managed.
    The EntityManager API is used to create and remove persistent entity (ApartmentPhoto) instances,
    to find entities by their primary key, and to query over entities (ApartmentPhotos).
    ---------------------------------------------------------------------------------------------
     */
    @PersistenceContext(unitName = "FlatmatesPU")
    private EntityManager em;

    // @Override annotation indicates that the super class AbstractFacade's getEntityManager() method is overridden.
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*
    This constructor method invokes the parent abstract class AbstractFacade.java's
    constructor method AbstractFacade, which in turn initializes its entityClass instance
    variable with the ApartmentPhoto class object reference returned by the ApartmentPhoto.class.
     */
    public ApartmentPhotoFacade() {
        super(ApartmentPhoto.class);
    }

    /*
    *********************************************************
    The following method is added to the auto generated code.
    *********************************************************
     */
    /**
     * @param primaryKey is the Primary Key of the Apartment entity in a table row in the database.
     * @return a list of photos associated with the Apartment whose primary key is primaryKey
     */
    public List<ApartmentPhoto> findPhotosByApartmentPrimaryKey(Integer primaryKey) {

        return (List<ApartmentPhoto>) em.createNamedQuery("ApartmentPhoto.findPhotosByApartmentDatabasePrimaryKey")
                .setParameter("primaryKey", primaryKey)
                .getResultList();
    }

    public ApartmentPhoto getApartmentPhoto(int id) {
        return em.find(ApartmentPhoto.class, id);
    }

    /**
     *
     * @param file_name
     * @return a list of object references of apartmentPhotos with the name file_name
     */
    public List<ApartmentPhoto> findByFilename(String file_name) {
        /*
        The following @NamedQuery definition is given in UserFile.java entity class file:
        @NamedQuery(name = "UserFile.findByFilename", query = "SELECT u FROM UserFile u WHERE u.filename = :filename")

        The following statement obtaines the results from the named database query.
         */
        List<ApartmentPhoto> files = em.createNamedQuery("ApartmentPhoto.findByFilename")
                .setParameter("filename", file_name)
                .getResultList();

        return files;
    }
}
