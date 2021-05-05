/* TODO comments
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang Siva. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Apartment;
import edu.vt.EntityBeans.ApartmentPhoto;
import edu.vt.FacadeBeans.ApartmentFacade;
import edu.vt.FacadeBeans.ApartmentPhotoFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.el.ELContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("apartmentPhotoController")
@SessionScoped

public class ApartmentPhotoController implements Serializable {

    /*
    cleanedFileNameHashMap<KEY, VALUE>
        KEY   = Integer fileId
        VALUE = String cleanedFileNameForSelected
     */
    HashMap<Integer, String> cleanedFileNameHashMap = null;
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private ApartmentPhoto selected;
    private List<ApartmentPhoto> items;
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
    // Selected row number in p:dataTable in ListPhotos.xhtml
    private String selectedRowNumber = "0";

    /*
    ==================
    Constructor Method
    ==================
     */
    public ApartmentPhotoController() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public List<ApartmentPhoto> getItems() {
        if (items == null) {
            // Get the ApartmentController for the current session
            ELContext elContext = FacesContext.getCurrentInstance().getELContext();
            ApartmentController apartmentController = (ApartmentController) elContext.getELResolver().getValue(elContext, null, "apartmentController");

            // Obtain only those files from the database that belong to the apartment
            //items = apartmentPhotoFacade.deleteSelectedUserFile(apartmentController.getSelected().getId());
            items = apartmentController.deleteSelectedUserFile(apartmentController.getSelected().getId());
            // Instantiate a new hash map object
            cleanedFileNameHashMap = new HashMap<>();

            /*
            cleanedFileNameHashMap<KEY, VALUE>
                KEY   = Integer fileId
                VALUE = String cleanedFileNameForSelected
             */
            // Java 8 looping over a list with lambda
            items.forEach(photoFile -> {

                // Obtain the filename stored in Team4-FileStorage/ApartmentPhotoStorage as 'userId_filename'
                String storedFileName = photoFile.getFilename();

                // Remove the "userId_" (e.g., "4_") prefix in the stored filename
                String cleanedFileName = storedFileName.substring(storedFileName.indexOf("_") + 1);

                // Obtain the file database Primary Key id
                Integer fileId = photoFile.getId();

                // Create an entry in the hash map as a key-value pair
                cleanedFileNameHashMap.put(fileId, cleanedFileName);
            });
        }
        return items;
    }

    public void setItems(List<ApartmentPhoto> items) {
        this.items = items;
    }

    public ApartmentPhoto getSelected() {
        return selected;
    }

    public void setSelected(ApartmentPhoto selected) {
        this.selected = selected;
    }

    public HashMap<Integer, String> getCleanedFileNameHashMap() {
        return cleanedFileNameHashMap;
    }

    public void setCleanedFileNameHashMap(HashMap<Integer, String> cleanedFileNameHashMap) {
        this.cleanedFileNameHashMap = cleanedFileNameHashMap;
    }

    public String getSelectedRowNumber() {
        return selectedRowNumber;
    }

    public void setSelectedRowNumber(String selectedRowNumber) {
        this.selectedRowNumber = selectedRowNumber;
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

    /*
    ================
    Instance Methods
    ================
     */

    public ApartmentPhoto prepareCreate() {
        // Instantiate a new ApartmentPhoto object and store its object reference into instance variable 'selected'
        // The ApartmentPhoto class is defined in ApartmentPhoto.java
        selected = new ApartmentPhoto();
        return selected;
    }

    /*
    ***************************
    Create New Apartment Photo
    ***************************
     */
    public void create() {
        persist(JsfUtil.PersistAction.CREATE, "Photo was Successfully Created!");

        /*
        JsfUtil.isValidationFailed() returns TRUE if the validationFailed() method has been called
        for the current request. Return of FALSE means that the create operation was successful and
        we can reset items to null so that it will be recreated with the newly created user file.
         */
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    // We do not allow update of a Apartment Photo.
    public void update() {
        persist(JsfUtil.PersistAction.UPDATE, "Photo was Successfully Updated!");
    }

    public void destroy() {

        Methods.preserveMessages();
//        try {
////            Files.deleteIfExists(Paths.get(selected.getFilePath()));
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }

        persist(JsfUtil.PersistAction.DELETE, "Photo was Successfully Deleted from the Database!");
        /*
        JsfUtil.isValidationFailed() returns TRUE if the validationFailed() method has been called
        for the current request. Return of FALSE means that the destroy operation was successful and
        we can reset items to null so that it will be recreated without the destroyed user file.
         */
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /*
    =========================
    Delete Selected User Photo
    =========================
     */
    public String deleteSelectedUserFile() {

        ApartmentPhoto userFileToDelete = selected;

        /*
        We need to preserve the messages since we will redirect to show a
        different JSF page after successful deletion of the user file.
         */
        Methods.preserveMessages();

        if (userFileToDelete == null) {
            Methods.showMessage("Fatal Error", "No File Selected!", "You do not have a file to delete!");
            return "";
        } else {
            try {
                // Delete the file from CloudStorage/FileStorage
                Files.deleteIfExists(Paths.get(userFileToDelete.getFilePath()));

                // Delete the user file record from the database
                getApartmentPhotoFacade().remove(userFileToDelete);
                // ApartmentPhotoFacade inherits the remove() method from AbstractFacade

                Methods.showMessage("Information", "Success!", "Selected File is Successfully Deleted!");

                // See method below
                refreshFileList();

                return "/usersApartment/ListPhotos?faces-redirect=true";

            } catch (IOException ex) {
                Methods.showMessage("Fatal Error", "Something went wrong while deleting the user file!",
                        "See: " + ex.getMessage());
                return "";
            }
        }

//        ApartmentPhoto apartmentPhotoToDelete = selected;
//
//        /*
//        We need to preserve the messages since we will redirect to show a
//        different JSF page after successful deletion of the user file.
//         */
//        Methods.preserveMessages();
//
//        if (apartmentPhotoToDelete == null) {
//            System.out.println("apartmentPhotoToDelete == null");
//            Methods.showMessage("Fatal Error", "No Photo Selected!", "You do not have a photo to delete!");
//            return "";
//        } else {
//            try {
//
//                // Delete the file from CloudStorage/FileStorage TODO
//                Files.deleteIfExists(Paths.get(apartmentPhotoToDelete.getFilePath()));
//                System.out.println("deleteIfExists");
//
//                // Delete the user file record from the database
//                persist(JsfUtil.PersistAction.DELETE, "Selected photo is successfully deleted!");
//                // apartmentPhotoFacade inherits the remove() method from AbstractFacade
//                System.out.println("remove");
//                Methods.showMessage("Information", "Success!", "Selected photo is successfully deleted!");
//
//                // See method below
//                refreshFileList();
//                System.out.println("refreshFileList");
//                return "/usersApartment/ListPhotos?faces-redirect=true";
//
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//                Methods.showMessage("Fatal Error", "Something went wrong while deleting the photo!",
//                        "See: " + ex.getMessage());
//                return "";
//            }
//        }
    }

    /*
    ========================
    Refresh User's Photo List
    ========================
     */
    public void refreshFileList() {
        /*
        By setting the items to null, we force the getItems
        method above to retrieve all of the user's files again.
         */
        selected = null; // Remove selection
        items = null;    // Invalidate list of items to trigger re-query.
    }

    /*
    =====================================
    Return Cleaned Filename given Photo Id
    =====================================
     */
    public String cleanedFilenameForFileId(Integer fileId) {
        /*
        cleanedFileNameHashMap<KEY, VALUE>
            KEY   = Integer fileId
            VALUE = String cleanedFileNameForSelected
         */

        // Obtain the cleaned filename for the given fileId
        String cleanedFileName = cleanedFileNameHashMap.get(fileId);

        return cleanedFileName;
    }

    /*
    =========================================
    Return Cleaned Filename for Selected Photo
    =========================================
     */
    // This method is called from UserFiles.xhtml by passing the fileId as a parameter.
    public String cleanedFileNameForSelected() {

        Integer fileId = selected.getId();
        /*
        cleanedFileNameHashMap<KEY, VALUE>
            KEY   = Integer fileId
            VALUE = String cleanedFileNameForSelected
         */

        // Obtain the cleaned filename for the given fileId
        String cleanedFileName = cleanedFileNameHashMap.get(fileId);

        return cleanedFileName;
    }

    /*
    ==========================
    Return Selected Photo's URI
    ==========================
     */
    public String selectedFileURI() {
        return Constants.APARTMENT_PHOTOS_URI + selected.getFilename();
    }

    /*
    =============================================
    Return True if Selected Photo is an Image Photo
    =============================================
     */
    public boolean isImage() {

        switch (extensionOfSelectedFileInCaps()) {
            case "JPG":
            case "JPEG":
            case "PNG":
            case "GIF":
                // Selected file is an acceptable image file
                return true;
            default:
                return false;
        }
    }

    /*
    ========================================================
    Return Extension of the Selected Photo in Capital Letters
    ========================================================
     */
    public String extensionOfSelectedFileInCaps() {

        // Obtain the selected filename
        String userFileName = selected.getFilename();

        // Extract the file extension from the filename
        String fileExtension = userFileName.substring(userFileName.lastIndexOf(".") + 1);

        // Convert file extension to be in capital letters
        String fileExtensionInCaps = fileExtension.toUpperCase();

        return fileExtensionInCaps;
    }

    /*
     ****************************************************************************
     *   Perform CREATE, EDIT (UPDATE), and DELETE Operations in the Database   *
     ****************************************************************************
     */

    /**
     * @param persistAction  refers to CREATE, UPDATE (Edit) or DELETE action
     * @param successMessage displayed to inform the user about the result
     */
    private void persist(JsfUtil.PersistAction persistAction, String successMessage) {

        if (selected != null) {

            try {
                if (persistAction != JsfUtil.PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).

                     UserFileFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    apartmentPhotoFacade.edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove method performs the DELETE operation in the database.

                     UserFileFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    apartmentPhotoFacade.remove(selected);
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
                    JsfUtil.addErrorMessage(ex, "A Persistence Error Occurred!");
                }
            } catch (Exception ex) {
                System.out.println(12);
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, "A Persistence Error Occurred!");
            }
        }
    }

    @FacesConverter(forClass = ApartmentPhoto.class)
    public static class ApartmentPhotoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ApartmentPhotoController controller = (ApartmentPhotoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "apartmentPhotoController");
            return controller.getApartmentPhotoFacade().find(getKey(value));
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
            if (object instanceof ApartmentPhoto) {
                ApartmentPhoto o = (ApartmentPhoto) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ApartmentPhoto.class.getName());
            }
        }

    }

}
