/*
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang, Siva. All rights reserved.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Apartment;
import edu.vt.EntityBeans.ApartmentPhoto;
import edu.vt.globals.Methods;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Stateless
public class ApartmentFacade extends AbstractFacade<Apartment> {
    /*
    ---------------------------------------------------------------------------------------------
    Annotating 'private EntityManager em;' with '@PersistenceContext(unitName = "FlatmatesPU")'
    implies that the EntityManager instance pointed to by 'em' is associated with the 'FlatmatesPU'
    persistence context. The persistence context is a set of entity (Apartment) instances in which for
    any persistent entity (Apartment) identity, there is a unique entity (Apartment) instance.
    Within the persistence context, the entity (Apartment) instances and their life cycle are managed.
    The EntityManager API is used to create and remove persistent entity (Apartment) instances,
    to find entities by their primary key, and to query over entities (Apartments).
    ---------------------------------------------------------------------------------------------
     */
    @PersistenceContext(unitName = "FlatmatesPU")
    private EntityManager em;

    // @Override annotation indicates that the super class AbstractFacade's getEntityManager() method is overridden.
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @EJB
    private ApartmentPhotoFacade apartmentPhotoFacade;

    /*
    This constructor method invokes the parent abstract class AbstractFacade.java's
    constructor method AbstractFacade, which in turn initializes its entityClass instance
    variable with the Apartment class object reference returned by the Apartment.class.
     */
    public ApartmentFacade() {
        super(Apartment.class);
    }

    /*
    ***********************************************************
    The following methods are added to the auto generated code.
    ***********************************************************
     */
    /**
     * @param id is the Primary Key of the Apartment entity in a table row in the database.
     * @return object reference of the Apartment object whose primary key is id
     */
    public Apartment getApartment(int id) {
        // The find method is inherited from the parent AbstractFacade class
        return em.find(Apartment.class, id);
    }

    public ApartmentPhotoFacade getApartmentPhotoFacade() {
        return apartmentPhotoFacade;
    }

    public void setApartmentPhotoFacade(ApartmentPhotoFacade apartmentPhotoFacade) {
        this.apartmentPhotoFacade = apartmentPhotoFacade;
    }

    /**
     * @param id is the user_id attribute (column) value of the Apartment
     * @return object reference of the List of Apartment entities whose user id is user_id
     */
    public List<Apartment> findApartmentsByUserPrimaryKey(int id) {
        return (List<Apartment>) em.createNamedQuery("Apartment.findByUserId")
                .setParameter("userId", id)
                .getResultList();
    }

    /**
     * Deletes the Apartment entity whose primary key is id
     * @param id is the Primary Key of the Apartment entity in a table row in the database.
     */
    public void deleteApartment(int id) {

        // The find method is inherited from the parent AbstractFacade class
        Apartment apartment = em.find(Apartment.class, id);

        // First delete any photos
        deleteAllApartmentPhotos(apartment.getId());

        // The remove method is inherited from the parent AbstractFacade class
        em.remove(apartment);
    }


    public List<Apartment> searchApartments(String searchQuery) {
        return (List<Apartment>) em.createQuery(searchQuery)
                .getResultList();
    }

    // Delete all apartment photos for apartment with id 'primaryKey'
    public void deleteAllApartmentPhotos(int primaryKey) {
        // Obtain the List of files that belongs to the user with primaryKey
        System.out.println("deleteAllApartmentPhotos for " + primaryKey);
        List<ApartmentPhoto> apartmentPhotoList = getApartmentPhotoFacade().findPhotosByApartmentPrimaryKey(primaryKey);
        System.out.println("apartmentPhotoList " + apartmentPhotoList.size());
        if (!apartmentPhotoList.isEmpty()) {
            // Java 8 looping over a list with lambda
            apartmentPhotoList.forEach(photo -> {
                System.out.println("photo.getFilePath() for " + photo.getFilePath());
                try {
                    /*
                    Delete the user file if it exists.
                    getFilePath() is given in UserFile.java.
                     */
                    Files.deleteIfExists(Paths.get(photo.getFilePath()));
                    // Remove the user's file record from the database
                    getApartmentPhotoFacade().remove(photo);

                } catch (IOException ex) {
                    System.out.println("See: " + ex.getMessage());
                    Methods.showMessage("Fatal Error",
                            "Something went wrong while deleting Apartment Photo files!",
                            "See: " + ex.getMessage());
                }
            });
        }
    }
}
