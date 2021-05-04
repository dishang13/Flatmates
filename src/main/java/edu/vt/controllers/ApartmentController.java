/*
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang Siva. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Apartment;
import edu.vt.EntityBeans.ApartmentPhoto;
import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.ApartmentFacade;
import edu.vt.FacadeBeans.ApartmentPhotoFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/*
---------------------------------------------------------------------------
The @Named (javax.inject.Named) annotation indicates that the objects
instantiated from this class will be managed by the Contexts and Dependency
Injection (CDI) container. The name "userController" is used within
Expression Language (EL) expressions in JSF (XHTML) facelets pages to
access the properties and invoke methods of this class.
---------------------------------------------------------------------------
 */
@Named("apartmentController")

/*
The @SessionScoped annotation preserves the values of the UserController
object's instance variables across multiple HTTP request-response cycles
as long as the user's established HTTP session is alive.
 */
@SessionScoped

/*
--------------------------------------------------------------------------
Marking the UserController class as "implements Serializable" implies that
instances of the class can be automatically serialized and deserialized.

Serialization is the process of converting a class instance (object)
from memory into a suitable format for storage in a file or memory buffer,
or for transmission across a network connection link.

Deserialization is the process of recreating a class instance (object)
in memory from the format under which it was stored.
--------------------------------------------------------------------------
 */
public class ApartmentController implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private Integer id;
    private String name;
    private String description;
    private Date dateEntered;
    private boolean archived;
    private String address;
    private int numBed;
    private int numBath;
    private int rent;
    private Date startDate;
    private Date endDate;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String complexWebsite;

    private List<Apartment> items;
    private List<ApartmentPhoto> apartmentPhotoList;

    private Apartment selected;

    /*
    The instance variable 'apartmentFacade' is annotated with the @EJB annotation.
    The @EJB annotation directs the EJB Container (of the GlassFish AS) to inject (store) the object reference
    of the ApartmentFacade object, after it is instantiated at runtime, into the instance variable 'apartmentFacade'.
     */
    @EJB
    private ApartmentFacade apartmentFacade;

    /*
    The instance variable 'apartmentPhotoFacade' is annotated with the @EJB annotation.
    The @EJB annotation directs the EJB Container (of the GlassFish AS) to inject (store) the object reference
    of the ApartmentPhotoFacade object, after it is instantiated at runtime, into the instance variable 'apartmentPhotoFacade'.
     */
    @EJB
    private ApartmentPhotoFacade apartmentPhotoFacade;

    /*
    ==================
    Constructor Method
    ==================
     */
    public ApartmentController() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEntered() {
        return dateEntered;
    }

    public void setDateEntered(Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<ApartmentPhoto> getApartmentPhotoList() {
        if(apartmentPhotoList == null) {
            // Store the object reference of the apartment photos into the instance variable selected.
            apartmentPhotoList = apartmentPhotoFacade.findPhotosByApartmentPrimaryKey(id);
        }
        return apartmentPhotoList;
    }

    public void setApartmentPhotoList(List<ApartmentPhoto> apartmentPhotoList) {
        this.apartmentPhotoList = apartmentPhotoList;
    }

    public Apartment getSelected() {
        return selected;
    }

    public void setSelected(Apartment selected) {
        this.selected = selected;
    }

    public ApartmentFacade getApartmentFacade() {
        return apartmentFacade;
    }

    public void setApartmentFacade(ApartmentFacade apartmentFacade) {
        this.apartmentFacade = apartmentFacade;
    }

    public ApartmentPhotoFacade getApartmentPhotoFacade() {
        return apartmentPhotoFacade;
    }

    public void setApartmentPhotoFacade(ApartmentPhotoFacade apartmentPhotoFacade) {
        this.apartmentPhotoFacade = apartmentPhotoFacade;
    }

    public void unselect() {
        selected = null;
    }

    public List<Apartment> getItems() {
        if(items == null) {
            /*
            'user', the object reference of the signed-in user, was put into the SessionMap
            in the initializeSessionMap() method in LoginManager upon user's sign in.
             */
            User signedInUser = (User) Methods.sessionMap().get("user");

            // Obtain only those apartments from the database that belong to the signed-in user
            items = getApartmentFacade().findApartmentsByUserPrimaryKey(signedInUser.getId());
        }
        return items;
    }

    public void setItems(List<Apartment> items) {
        this.items = items;
    }

    /*
    ================
    Instance Methods
    ================
    */

    /*
    *************************************
    Prepare to Create a New Apartment
    *************************************
     */
    public Apartment prepareCreate() {
        /*
        Instantiate a new Apartment object and store its object reference into instance
        variable 'selected'. The Apartment class is defined in Apartment.java
         */
        selected = new Apartment();
        // Return the object reference of the newly created Apartment object
        return selected;
    }

    /*
    ******************************************
    Create a New Apartment in the Database
    ******************************************
     */
    public void create() {
        /*
        We need to preserve the messages since we will redirect to show a
        different JSF page after successful creation of the Survey.
         */
        Methods.preserveMessages();

        // Set userId attribute value
        User signedInUser = (User) Methods.sessionMap().get("user");
        selected.setUserId(signedInUser);


        // Set dateEntered attribute value
        LocalDate localDate = LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(localDate);

        // Set Date in the format of YYYY-MM-DD
        selected.setDateEntered(currentDate);
        selected.setLatitude(new BigDecimal(0.0));
        selected.setLongitude(new BigDecimal(0.0));
        /*
        Show the message "Your Apartment has been successfully listed!"

        Prevent displaying the same msg twice, one for Summary and one for Detail, by setting the
        message Detail to "" in the addSuccessMessage(String msg) method in the jsfUtil.java file.
         */
        persist(PersistAction.CREATE,"Your Apartment has been successfully listed!");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The CREATE operation is successfully performed.
            selected = null; // Remove selection
            items = null; // Invalidate list to trigger requery
        }
    }

    // TODO
    public void update() { // TODO
        persist(PersistAction.UPDATE,"Apartment was successfully updated.");
    }

    /*
    ***********************************************
    Delete the Selected Apartment from the Database
    ***********************************************
    */
    public void delete() {
        /*
        We need to preserve the messages since we will redirect to show a
        different JSF page after successful deletion of the Survey.
         */
        Methods.preserveMessages();
        /*
        Show the message "The Apartment was successfully deleted!"

        Prevent displaying the same msg twice, one for Summary and one for Detail, by setting the
        message Detail to "" in the addSuccessMessage(String msg) method in the jsfUtil.java file.
         */
        persist(PersistAction.DELETE,"The Apartment was successfully deleted!\n");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The DELETE operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /*
    ****************************************
    Archive/Unarchive the Selected Apartment
    ****************************************
    */
    public void archive() {
        /*
        We need to preserve the messages since we will redirect to show a
        different JSF page after successful deletion of the Survey.
         */
        Methods.preserveMessages();
        selected.setArchived(!selected.getArchived());
        /*
        Show the message "The Apartment was successfully archived!"

        Prevent displaying the same msg twice, one for Summary and one for Detail, by setting the
        message Detail to "" in the addSuccessMessage(String msg) method in the jsfUtil.java file.
         */
        persist(PersistAction.UPDATE, selected.getArchived() ? "The Apartment was successfully archived!\n"
                                                             : "The Apartment was successfully unarchived!\n");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The DELETE operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /*
    ******************************************************
    *   Cancel to Display List.xhtml JSF Facelets Page   *
    ******************************************************
    */
    public String cancel() {
        // Unselect previously selected apartment if any
        selected = null;
        items = null;
        return "/usersApartment/List?faces-redirect=true";
    }

    /*
    **************************************
    Return List of U.S. State Postal Codes
    **************************************
     */
    public String[] listOfStates() {
        return Constants.STATES;
    }

    /*
     ****************************************************************************
     *   Perform CREATE, EDIT (UPDATE), and DELETE Operations in the Database   *
     ****************************************************************************
     */
    /** TODO: check edit/delete
     * @param persistAction refers to CREATE, UPDATE (Edit) or DELETE action
     * @param successMessage displayed to inform the user about the result
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).

                     ApartmentFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    getApartmentFacade().edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove method performs the DELETE operation in the database.

                     ApartmentFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    getApartmentFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);

            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
            }
        }
    }

    @FacesConverter(forClass = Apartment.class)
    public static class ApartmentControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ApartmentController controller = (ApartmentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "apartmentController");
            return controller.getApartmentFacade().find(getKey(value));
        }

        Integer getKey(String value) {
            Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Apartment) {
                Apartment o = (Apartment) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Apartment.class.getName());
            }
        }
    }

    /*
     ****************************
     *   Return Logo File URI   *
     ****************************
     */
    public String logoFileStoragePath() {
        return Constants.APARTMENT_PHOTOS_URI;
    }

    /*
     *****************************************
     *   Delete Uploaded Company Logo File   *
     *****************************************
     */
//    public void deleteLogoFile() {
//
//        // This sets the necessary flag to ensure the messages are preserved.
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//
//        // Delete the company logo file stored in a directory external to the app directory
//        try {
//            // Obtain the company logo file URI
//            String companyLogoFileURI = Constants.APARTMENT_PHOTOS_URI + getSelected().getTicker() + ".png";
//
//            // Obtain a Path object by converting the company logo file URI
//            Path fileToDeletePath = Paths.get(companyLogoFileURI);
//
//            // Delete the file under the Path object if it exists
//            Files.deleteIfExists(fileToDeletePath);
//
//            // Unselect previously selected company if any
//            selected = null;
//            setLogoFileUploaded(false);
//
//        } catch (IOException ex) {
//            Methods.showMessage("Fatal Error", "Something went wrong during logo file deletion!",
//                    "See: " + ex.getMessage());
//        }
//    }

}
