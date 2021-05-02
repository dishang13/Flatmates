/*
 * Created by Dishang R Valotia on 2021.4.21
 * Copyright © 2021 Dishang R Valotia. All rights reserved.
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
    Annotating 'private EntityManager em;' with '@PersistenceContext(unitName = "FlatmatesPU")'
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
}
