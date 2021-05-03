/*
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang, Siva. All rights reserved.
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// The @Entity annotation designates this class as a JPA Entity class representing the Apartment table in the FlatmatesDB database.
@Entity

// Name of the database table represented
@Table(name = "Apartment")

@NamedQueries({
    @NamedQuery(name = "Apartment.findAll", query = "SELECT a FROM Apartment a"),
    @NamedQuery(name = "Apartment.findById", query = "SELECT a FROM Apartment a WHERE a.id = :id"),
    @NamedQuery(name = "Apartment.findByName", query = "SELECT a FROM Apartment a WHERE a.name = :name"),
    @NamedQuery(name = "Apartment.findByDateEntered", query = "SELECT a FROM Apartment a WHERE a.dateEntered = :dateEntered"),
    @NamedQuery(name = "Apartment.findByArchived", query = "SELECT a FROM Apartment a WHERE a.archived = :archived"),
    @NamedQuery(name = "Apartment.findByAddress1", query = "SELECT a FROM Apartment a WHERE a.address1 = :address1"),
    @NamedQuery(name = "Apartment.findByAddress2", query = "SELECT a FROM Apartment a WHERE a.address2 = :address2"),
    @NamedQuery(name = "Apartment.findByCity", query = "SELECT a FROM Apartment a WHERE a.city = :city"),
    @NamedQuery(name = "Apartment.findByState", query = "SELECT a FROM Apartment a WHERE a.state = :state"),
    @NamedQuery(name = "Apartment.findByNumBed", query = "SELECT a FROM Apartment a WHERE a.numBed = :numBed"),
    @NamedQuery(name = "Apartment.findByNumBath", query = "SELECT a FROM Apartment a WHERE a.numBath = :numBath"),
    @NamedQuery(name = "Apartment.findByRent", query = "SELECT a FROM Apartment a WHERE a.rent = :rent"),
    @NamedQuery(name = "Apartment.findByStartDate", query = "SELECT a FROM Apartment a WHERE a.startDate = :startDate"),
    @NamedQuery(name = "Apartment.findByEndDate", query = "SELECT a FROM Apartment a WHERE a.endDate = :endDate"),
    @NamedQuery(name = "Apartment.findByLatitude", query = "SELECT a FROM Apartment a WHERE a.latitude = :latitude"),
    @NamedQuery(name = "Apartment.findByLongitude", query = "SELECT a FROM Apartment a WHERE a.longitude = :longitude"),
    @NamedQuery(name = "Apartment.findByComplexWebsite", query = "SELECT a FROM Apartment a WHERE a.complexWebsite = :complexWebsite"),
    @NamedQuery(name = "Apartment.findByPetsAllowed", query = "SELECT a FROM Apartment a WHERE a.petsAllowed = :petsAllowed"),
    @NamedQuery(name = "Apartment.findByUserId", query = "SELECT a FROM Apartment a WHERE a.userId.id = :userId")
})

public class Apartment implements Serializable {

    /*
    ========================================================
    Instance variables representing the attributes (columns)
    of the Apartment table in the FlatmatesDB database.
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
    @Size(min = 1, max = 256)
    @Column(nullable = false, length = 256)
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "date_entered", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateEntered;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean archived;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(nullable = false, length = 128)
    private String address1;

    @Size(max = 128)
    @Column(length = 128)
    private String address2;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(nullable = false, length = 64)
    private String city;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String state;

    @Basic(optional = false)
    @NotNull
    @Column(name = "num_bed", nullable = false)
    private int numBed;

    @Basic(optional = false)
    @NotNull
    @Column(name = "num_bath", nullable = false)
    private int numBath;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int rent;

    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 8, scale = 6)
    private BigDecimal latitude;

    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;

    @Size(max = 1028)
    @Column(name = "complex_website", length = 1028)
    private String complexWebsite;

    @Basic(optional = false)
    @NotNull
    @Column(name = "pets_allowed", nullable = false)
    private boolean petsAllowed;

    // User Id
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "apartmentId")
    private Collection<ApartmentPhoto> apartmentPhotoCollection;

    /*
    ============================================================
    Class constructors for instantiating a Apartment entity object to
    represent a row in the User table in the FlatmatesDB database.
    ============================================================
     */
    public Apartment() {
    }

    public Apartment(Integer id) {
        this.id = id;
    }

    public Apartment(Integer id, String name, Date dateEntered, boolean archived, String address1, String city, String state, int numBed, int numBath, int rent, Date startDate, Date endDate, BigDecimal latitude, BigDecimal longitude, boolean petsAllowed) {
        this.id = id;
        this.name = name;
        this.dateEntered = dateEntered;
        this.archived = archived;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.numBed = numBed;
        this.numBath = numBath;
        this.rent = rent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.petsAllowed = petsAllowed;
    }

    /*
    ======================================================
    Getter and Setter methods for the attributes (columns)
    of the Apartment table in the FlatmatesDB database.
    ======================================================
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public boolean getArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumBed() {
        return numBed;
    }

    public void setNumBed(int numBed) {
        this.numBed = numBed;
    }

    public int getNumBath() {
        return numBath;
    }

    public void setNumBath(int numBath) {
        this.numBath = numBath;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getComplexWebsite() {
        return complexWebsite;
    }

    public void setComplexWebsite(String complexWebsite) {
        this.complexWebsite = complexWebsite;
    }

    public boolean getPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public Collection<ApartmentPhoto> getApartmentPhotoCollection() {
        return apartmentPhotoCollection;
    }

    public void setApartmentPhotoCollection(Collection<ApartmentPhoto> apartmentPhotoCollection) {
        this.apartmentPhotoCollection = apartmentPhotoCollection;
    }

    public boolean isArchived() {
        return archived;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
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
     * Checks if the Apartment object identified by 'object' is the same as the Apartment object identified by 'id'
     *
     * @param object The Apartment object identified by 'object'
     * @return True if the Apartment 'object' and 'id' are the same; otherwise, return False
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apartment)) {
            return false;
        }
        Apartment other = (Apartment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        // Convert the Apartment object's database primary key (Integer) to String type and return it.
        return id.toString();
    }
    
}
