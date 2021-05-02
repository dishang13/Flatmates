/*
 * Created by Dishang R Valotia on 2021.4.21
 * Copyright © 2021 Dishang R Valotia. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Apartment;
import edu.vt.EntityBeans.ApartmentPhoto;
import edu.vt.EntityBeans.User;
import edu.vt.EntityBeans.UserPhoto;
import edu.vt.FacadeBeans.ApartmentFacade;
import edu.vt.FacadeBeans.ApartmentPhotoFacade;
import edu.vt.FacadeBeans.UserFacade;
import edu.vt.FacadeBeans.UserPhotoFacade;
import edu.vt.globals.Constants;
import edu.vt.globals.Methods;
import org.imgscalr.Scalr;
import org.primefaces.model.UploadedFile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
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
@Named("apartmentPhotoController")

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
public class ApartmentPhotoController implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private List<UploadedFile> fileList;

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
    public ApartmentPhotoController() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public List<UploadedFile> getFileList() {
        return fileList;
    }

    public void setFile(List<UploadedFile> fileList) {
        this.fileList = fileList;
    }

    public ApartmentFacade getApartmentFacade() {
        return apartmentFacade;
    }

    public ApartmentPhotoFacade getApartmentPhotoFacade() {
        return apartmentPhotoFacade;
    }

    /*
    ================
    Instance Methods
    ================

    ************************
    Handle User Photo Upload
    ************************
     */
//    public String upload() {
//        /*
//        Redirecting to show a JSF page involves more than one subsequent requests and
//        the messages would die from one request to another if not kept in the Flash scope.
//        Since we will redirect to show the Profile page, we invoke preserveMessages().
//        */
//        Methods.preserveMessages();
//
//        // Check if a file is selected
//        if (file.getSize() == 0) {
//            Methods.showMessage("Information", "No File Selected!",
//                    "You need to choose a file first before clicking Upload.");
//            return "";
//        }
//
//        /*
//        MIME (Multipurpose Internet Mail Extensions) is a way of identifying files on
//        the Internet according to their nature and format.
//
//        A "Content-type" is simply a header defined in many protocols, such as HTTP, that
//        makes use of MIME types to specify the nature of the file currently being handled.
//
//        Some MIME file types: (See http://www.freeformatter.com/mime-types-list.html)
//
//            JPEG Image      = image/jpeg or image/jpg
//            PNG image       = image/png
//            GIF image       = image/gif
//            Plain text file = text/plain
//            HTML file       = text/html
//            JSON file       = application/json
//         */
//        // Obtain the uploaded file's MIME file type
//        String mimeFileType = file.getContentType();
//
//        if (mimeFileType.startsWith("image/")) {
//            // The uploaded file is an image file
//            /*
//            The subSequence() method returns the portion of the mimeFileType string from the 6th
//            position to the last character. Note that it starts with "image/" which has 6 characters at
//            positions 0,1,2,3,4,5. Therefore, we start the subsequence at position 6 to obtain the file extension.
//             */
//            String fileExtension = mimeFileType.subSequence(6, mimeFileType.length()).toString();
//
//            String fileExtensionInCaps = fileExtension.toUpperCase();
//
//            switch (fileExtensionInCaps) {
//                case "JPG":
//                case "JPEG":
//                case "PNG":
//                case "GIF":
//                    // File is an acceptable image type
//                    break;
//                default:
//                    Methods.showMessage("Fatal Error", "Unrecognized File Type!",
//                            "Selected file type is not a JPG, JPEG, PNG, or GIF!");
//                    return "";
//            }
//        } else {
//            Methods.showMessage("Fatal Error", "Unrecognized File Type!",
//                    "Selected file type is not a JPG, JPEG, PNG, or GIF!");
//            return "";
//        }
//
//        storePhotoFile(file);
//
//        // Redirect to show the Profile page
//        return "/userAccount/Profile?faces-redirect=true";
//    }
//
//    /*
//    *******************************************************
//    Store Uploaded User's Photo File and its Thumbnail File
//    *******************************************************
//     */
//    public String storePhotoFile(UploadedFile file) {
//
//        // Since we will redirect to show the Profile page, invoke preserveMessages()
//        Methods.preserveMessages();
//
//        try {
//            // Delete signedInUser's earlier uploaded photo file, its thumbnail file, and its database record.
//            deletePhoto();
//
//            // Obtain the object reference of the signed-in User object
//            User signedInUser = (User) Methods.sessionMap().get("user");
//
//            // Obtain the uploaded file's MIME file type
//            String mimeFileType = file.getContentType();
//
//            // If it is an image file, obtain its file extension; otherwise, set png as the file extension anyway.
//            String fileExtension = mimeFileType.startsWith("image/") ? mimeFileType.subSequence(6, mimeFileType.length()).toString() : "png";
//
//            // Construct a new Photo object with file extension and signedInUser's object reference
//            UserPhoto newPhoto = new UserPhoto(fileExtension, signedInUser);
//
//            // Create a record for the new Photo object in the database
//            getUserPhotoFacade().create(newPhoto);
//
//            /*
//            Obtain the object reference of the first Photo object of the signedInUser
//            whose primary key is signedInUser.getId()
//            */
//            UserPhoto photo = getUserPhotoFacade().findPhotosByUserPrimaryKey(signedInUser.getId()).get(0);
//
//            /*
//            InputStream is an abstract class, which is the superclass of all classes representing
//            an input stream of bytes. It is imported as: import java.io.InputStream;
//            Convert the uploaded file into an input stream of bytes.
//             */
//            InputStream inputStream = file.getInputstream();
//
//            // Write the uploaded file's input stream of bytes under the photo object's
//            // filename using the inputStreamToFile method given below
//            File uploadedFile = inputStreamToFile(inputStream, photo.getPhotoFilename());
//
//            // Create and save the thumbnail version of the uploaded file
//            saveThumbnail(uploadedFile, photo);
//
//            Methods.showMessage("Information", "Success!", "User's Photo File is Successfully Uploaded!");
//
//        } catch (IOException ex) {
//            Methods.showMessage("Fatal Error", "Something went wrong while storing the user's photo file!",
//                    "See: " + ex.getMessage());
//        }
//
//        // Redirect to show the Profile page
//        return "/userAccount/Profile?faces-redirect=true";
//    }
//
//    /*
//    ***************************************************
//    Write Given InputStream into a File with Given Name
//    ***************************************************
//     */
//    /**
//     * @param inputStream of bytes to be written into file with name targetFilename
//     * @param targetFilename
//     * @return the created file targetFile
//     * @throws IOException
//     */
//    private File inputStreamToFile(InputStream inputStream, String targetFilename) throws IOException {
//
//        File targetFile = null;
//
//        try {
//            /*
//            inputStream.available() returns an estimate of the number of bytes that can be read from
//            the inputStream without blocking by the next invocation of a method for this input stream.
//            A memory buffer of bytes is created with the size of estimated number of bytes.
//             */
//            byte[] buffer = new byte[inputStream.available()];
//
//            // Read the bytes of data from the inputStream into the created memory buffer.
//            inputStream.read(buffer);
//
//            // Create a new empty file with the given name targetFilename in the UserPhotoStorage directory
//            targetFile = new File(Constants.PHOTOS_ABSOLUTE_PATH, targetFilename);
//
//            // A file OutputStream is an output stream for writing data to a file
//            OutputStream outStream;
//
//            /*
//            FileOutputStream is intended for writing streams of raw bytes such as image data.
//            Create a new FileOutputStream for writing to the empty targetFile
//             */
//            outStream = new FileOutputStream(targetFile);
//
//            // Create the targetFile in the UserPhotoStorage directory with the inputStream given
//            outStream.write(buffer);
//
//            // Close the output stream and release any system resources associated with it.
//            outStream.close();
//
//        } catch (IOException ex) {
//            Methods.showMessage("Fatal Error", "Something went wrong in input stream to file!",
//                    "See: " + ex.getMessage());
//        }
//
//        // Return the created targetFile
//        return targetFile;
//    }
//
//    /*
//    ********************************************
//    Store Signed-In User's Thumbnail Photo Image
//    ********************************************
//
//    When signedInUser uploads a photo, a thumbnail (small) version of the photo image
//    is created in this method by using the Scalr.resize method provided in the
//    imgscalr (Java Image Scaling Library) imported as imgscalr-lib-4.2.jar
//     */
//    private void saveThumbnail(File inputFile, UserPhoto inputPhoto) {
//
//        try {
//            // Buffer the photo image from the uploaded inputFile
//            BufferedImage uploadedPhoto = ImageIO.read(inputFile);
//
//            /*
//            The thumbnail photo image size is set to 200x200px in Constants.java as follows:
//            public static final Integer THUMBNAIL_SIZE = 200;
//
//            If the signedInUser uploads a large photo file, we will scale it down to THUMBNAIL_SIZE
//            and use it so that the size is reasonable for performance reasons.
//
//            The photo image scaling is properly done by using the imgscalr-lib-4.2.jar file.
//
//            The thumbnail file is named as "userId_thumbnail.fileExtension",
//            e.g., 5_thumbnail.jpg for signedInUser with id 5.
//             */
//            // Scale the uploaded photo image to the THUMBNAIL_SIZE using imgscalr.
//            BufferedImage thumbnailPhoto = Scalr.resize(uploadedPhoto, Constants.THUMBNAIL_SIZE);
//
//            // Create the thumbnail photo file in the UserPhotoStorage directory
//            File thumbnailPhotoFile = new File(Constants.PHOTOS_ABSOLUTE_PATH, inputPhoto.getThumbnailFileName());
//
//            /*
//            NOTE: ImageIO is imported as: import javax.imageio.ImageIO;
//            Write the thumbnailPhoto into thumbnailPhotoFile with the file extension.
//            */
//            ImageIO.write(thumbnailPhoto, inputPhoto.getExtension(), thumbnailPhotoFile);
//
//        } catch (IOException ex) {
//            Methods.showMessage("Fatal Error", "Something went wrong while saving the thumbnail file!",
//                    "See: " + ex.getMessage());
//        }
//    }
//
//    /*
//    *******************************************************
//    Delete Signed-In User's Photo and Thumbnail Image Files
//    *******************************************************
//     */
//    public void deletePhoto() {
//
//        // Obtain the object reference of the signedInUser
//        User signedInUser = (User) Methods.sessionMap().get("user");
//
//        // Obtain the id (primary key in the database) of the signedInUser object
//        Integer primaryKey = signedInUser.getId();
//
//        /*
//        Obtain the list of Photo file objects that belong to the signedInUser whose
//        database primary key is primaryKey. The list will contain only one photo or nothing.
//        We allow only one profile photo for the user to upload.
//         */
//        List<UserPhoto> photoList = getUserPhotoFacade().findPhotosByUserPrimaryKey(primaryKey);
//
//        // photoList.isEmpty implies that the user has never uploaded a photo file
//        if (!photoList.isEmpty()) {
//
//            // Obtain the object reference of the first Photo object in the list
//            UserPhoto photo = photoList.get(0);
//
//            try {
//                /*
//                NOTE: Files and Paths are imported as
//                        import java.nio.file.Files;
//                        import java.nio.file.Paths;
//
//                 Delete the user's photo if it exists.
//                 getFilePath() is given in UserPhoto.java file.
//                 */
//                Files.deleteIfExists(Paths.get(photo.getPhotoFilePath()));
//
//                /*
//                 Delete the user's thumbnail image if it exists.
//                 getThumbnailFilePath() is given in UserPhoto.java file.
//                 */
//                Files.deleteIfExists(Paths.get(photo.getThumbnailFilePath()));
//
//                // Delete the photo file record from the database.
//                // UserPhotoFacade inherits the remove() method from AbstractFacade.
//                getUserPhotoFacade().remove(photo);
//
//            } catch (IOException ex) {
//                Methods.showMessage("Fatal Error",
//                        "Something went wrong while deleting the user photo file!",
//                        "See: " + ex.getMessage());
//            }
//        }
//
//    }






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
