/*
 * Created by Osman Balci on 2021.2.27
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// The @Entity annotation designates this class as a JPA Entity class representing the UserQuestionnaire table in the BevqDB database.
@Entity

// Name of the database table represented
@Table(name = "UserQuestionnaire")

@NamedQueries({
    @NamedQuery(name = "UserQuestionnaire.findAll", query = "SELECT u FROM UserQuestionnaire u")
    , @NamedQuery(name = "UserQuestionnaire.findById", query = "SELECT u FROM UserQuestionnaire u WHERE u.id = :id")
    , @NamedQuery(name = "UserQuestionnaire.findByDateEntered", query = "SELECT u FROM UserQuestionnaire u WHERE u.dateEntered = :dateEntered")
    , @NamedQuery(name = "UserQuestionnaire.findByFluidOuncesConsumed", query = "SELECT u FROM UserQuestionnaire u WHERE u.fluidOuncesConsumed = :fluidOuncesConsumed")
    , @NamedQuery(name = "UserQuestionnaire.findByKcalIntake", query = "SELECT u FROM UserQuestionnaire u WHERE u.kcalIntake = :kcalIntake")
    , @NamedQuery(name = "UserQuestionnaire.findQuestionnairesByUserPrimaryKey", query = "SELECT u FROM UserQuestionnaire u WHERE u.userId.id = :primaryKey")
})
/* 
 The findQuestionnairesByUserPrimaryKey query is added. The others are auto generated. 
 userId     = object reference of the User object (See below: private User userId;)
 userId.id  = User object's database Primary Key to be set
 primaryKey = User object's database Primary Key given
 */

public class UserQuestionnaire implements Serializable {

    /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the UserQuestionnaire table in the BevqDB database.
    ========================================================
     */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_entered")
    @Temporal(TemporalType.DATE)
    private Date dateEntered;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fluid_Ounces_Consumed")
    private float fluidOuncesConsumed;

    @Basic(optional = false)
    @NotNull
    @Column(name = "kcal_Intake")
    private float kcalIntake;

    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "questionnaire")
    private String questionnaire;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    /*
    =========================================================================
    Class constructors for instantiating a UserQuestionnaire entity object to
    represent a row in the UserQuestionnaire table in the BevqDB database.
    =========================================================================
     */
    public UserQuestionnaire() {
    }

    public UserQuestionnaire(Integer id) {
        this.id = id;
    }

    public UserQuestionnaire(Integer id, Date dateEntered, float fluidOuncesConsumed, float kcalIntake, String questionnaire, User userId) {
        this.id = id;
        this.dateEntered = dateEntered;
        this.fluidOuncesConsumed = fluidOuncesConsumed;
        this.kcalIntake = kcalIntake;
        this.questionnaire = questionnaire;
        this.userId = userId;
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    of the UserQuestionnaire table in the BevqDB database.
    ======================================================
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public float getFluidOuncesConsumed() {
        return fluidOuncesConsumed;
    }

    public void setFluidOuncesConsumed(float fluidOuncesConsumed) {
        this.fluidOuncesConsumed = fluidOuncesConsumed;
    }

    public float getKcalIntake() {
        return kcalIntake;
    }

    public void setKcalIntake(float kcalIntake) {
        this.kcalIntake = kcalIntake;
    }

    public String getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(String questionnaire) {
        this.questionnaire = questionnaire;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    /*
    ================
    Instance Methods
    ================
     */
    /**
     * @return Generates and returns a hash code value for the object with id
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if the UserQuestionnaire object identified by 'object' is the same as the UserQuestionnaire object identified by 'id'
     *
     * @param object The UserQuestionnaire object identified by 'object'
     * @return True if the UserQuestionnaire 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserQuestionnaire)) {
            return false;
        }
        UserQuestionnaire other = (UserQuestionnaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // Convert the UserQuestionnaire object's database primary key (Integer) to String type and return it.
        return id.toString();
    }

}
