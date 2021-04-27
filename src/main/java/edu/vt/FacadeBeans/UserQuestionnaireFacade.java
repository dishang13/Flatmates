/*
 * Created by Osman Balci on 2021.2.27
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.UserQuestionnaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class UserQuestionnaireFacade extends AbstractFacade<UserQuestionnaire> {

    /*
    Annotating 'private EntityManager em;' with '@PersistenceContext(unitName = "BevQPU")' implies that
    the EntityManager instance pointed to by 'em' is associated with the 'BevQPU' persistence context. 
    
    Here, Entity is the UserQuestionnaire object. The persistence context is a set of entity (UserQuestionnaire)
    instances in which for any persistent entity identity, there is a unique entity instance. 
    
    Within the persistence context, the entity instances and their life cycles are managed. The EntityManager API is used
    to create and remove persistent entity instances, to find entities by their primary key, and to query over entities.
     */
    @PersistenceContext(unitName = "BevQ-ValotiaPU")
    private EntityManager em;  // 'em' holds the object reference to the instantiated EntityManager object.

    // @Override annotation indicates that the super class AbstractFacade's getEntityManager() method is overridden.
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*
     This constructor method invokes the parent abstract class AbstractFacade.java's 
     constructor method, which in turn initializes its entityClass instance variable
     with the UserQuestionnaire class object reference returned by the UserQuestionnaire.class. 
     */
    public UserQuestionnaireFacade() {
        super(UserQuestionnaire.class);
    }

    /*
    ======================================================
    The following methods are added to the generated code.
    ======================================================
     */
    public UserQuestionnaire getUserQuestionnaire(int id) {
        return em.find(UserQuestionnaire.class, id);
    }

    /**
     * Find all questionnaires that belong to a User whose database primary key is dbPrimaryKey
     *
     * @param dbPrimaryKey is the Primary Key of the user entity in the database
     * @return a list of object references of userQuestionnaires that belong to the user whose database Primary Key = dbPrimaryKey
     */
    public List<UserQuestionnaire> findUserQuestionnairesByUserPrimaryKey(Integer dbPrimaryKey) {
        /*
        The following @NamedQuery is defined in UserQuestionnaire.java entity class file:
        @NamedQuery(name = "UserQuestionnaire.findQuestionnairesByUserPrimaryKey", 
            query = "SELECT u FROM UserQuestionnaire u WHERE u.userId.id = :primaryKey")
        
        The following statement obtaines the results from the named database query.
         */
        List<UserQuestionnaire> userQuestionnaires = em.createNamedQuery("UserQuestionnaire.findQuestionnairesByUserPrimaryKey")
                .setParameter("primaryKey", dbPrimaryKey)
                .getResultList();

        return userQuestionnaires;
    }

}
