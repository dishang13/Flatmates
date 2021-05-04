/*
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang, Siva. All rights reserved.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Apartment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

        // The remove method is inherited from the parent AbstractFacade class
        em.remove(apartment);

        //TODO remove apartment photos
    }


    public List<Apartment> searchApartments(String searchQuery) {
        return (List<Apartment>) em.createQuery(searchQuery)
                .getResultList();
    }
}
