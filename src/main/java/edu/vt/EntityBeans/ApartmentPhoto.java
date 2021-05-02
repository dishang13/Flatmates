/*
 * Created by Dishang R Valotia on 2021.4.21
 * Copyright © 2021 Dishang R Valotia. All rights reserved.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// The @Entity annotation designates this class as a JPA Entity class representing the ApartmentPhoto table in the FlatmatesDB database.
@Entity

// Name of the database table represented
@Table(name = "ApartmentPhoto")

@NamedQueries({
    /*
    The following query is added. The others are auto generated.
    private Apartment apartmentId; --> apartmentId is the object reference of the apartment.
    apartmentId.id --> apartment object's database primary key
    */
    @NamedQuery(name = "ApartmentPhoto.findPhotosByApartmentDatabasePrimaryKey", query = "SELECT a FROM ApartmentPhoto a WHERE a.apartmentId.id = :primaryKey"),
    @NamedQuery(name = "ApartmentPhoto.findAll", query = "SELECT a FROM ApartmentPhoto a"),
    @NamedQuery(name = "ApartmentPhoto.findById", query = "SELECT a FROM ApartmentPhoto a WHERE a.id = :id"),
    @NamedQuery(name = "ApartmentPhoto.findByExtension", query = "SELECT a FROM ApartmentPhoto a WHERE a.extension = :extension")
})

public class ApartmentPhoto implements Serializable {

    /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the ApartmentPhoto table in the FlatmatesDB database.
    ========================================================
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(nullable = false, length = 4)
    private String extension;

    /*
    apartmentId is the unique object reference of the Apartment object auto generated
    by the system as an Integer value starting with 1 and incremented by 1,
    i.e., 1,2,3,... A deleted Apartment's object reference number is not reused.
    */
    @JoinColumn(name = "apartment_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Apartment apartmentId;

    /*
    =================================================================
    Class constructors for instantiating a ApartmentPhoto entity object to
    represent a row in the ApartmentPhoto table in the FlatmatesDB database.
    =================================================================
     */
    public ApartmentPhoto() {
    }

    public ApartmentPhoto(Integer id) {
        this.id = id;
    }

    public ApartmentPhoto(Integer id, String extension) {
        this.id = id;
        this.extension = extension;
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    of the ApartmentPhoto table in the FlatmatesDB database.
    ======================================================
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public Apartment getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Apartment apartmentId) {
        this.apartmentId = apartmentId;
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
     * Checks if the ApartmentPhoto object identified by 'object' is the same as the ApartmentPhoto object identified by 'id'
     *
     * @param object The ApartmentPhoto object identified by 'object'
     * @return True if the ApartmentPhoto 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApartmentPhoto)) {
            return false;
        }
        ApartmentPhoto other = (ApartmentPhoto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // Convert the ApartmentPhoto object's database primary key (Integer) to String type and return it.
        return id.toString();
    }
}
