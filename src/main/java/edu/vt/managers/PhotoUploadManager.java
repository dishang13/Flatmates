/*
 * Created by Osman Balci on 2021.2.10
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.managers;

import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.ApartmentFacade;
import edu.vt.FacadeBeans.ApartmentPhotoFacade;
import edu.vt.controllers.ApartmentPhotoController;
import edu.vt.globals.Constants;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@Named(value = "fileUploadManager")
@SessionScoped

public class PhotoUploadManager implements Serializable {
//    /*
//    ===============================
//    Instance Variables (Properties)
//    ===============================
//     */
//    private UploadedFile uploadedFile;
//
//    /*
//    The instance variable 'apartmentFacade' is annotated with the @EJB annotation.
//    The @EJB annotation directs the EJB Container (of the WildFly AS) to inject (store) the object reference
//    of the ApartmentFacade object, after it is instantiated at runtime, into the instance variable 'apartmentFacade'.
//     */
//    @EJB
//    private ApartmentFacade apartmentFacade;
//
//    /*
//    The instance variable 'apartmentPhotoFacade' is annotated with the @EJB annotation.
//    The @EJB annotation directs the EJB Container (of the WildFly AS) to inject (store) the object reference
//    of the ApartmentPhotoFacade object, after it is instantiated at runtime, into the instance variable 'apartmentPhotoFacade'.
//     */
//    @EJB
//    private ApartmentPhotoFacade apartmentPhotoFacade;
//
//    /*
//    The instance variable 'apartmentPhotoController' is annotated with the @Inject annotation.
//    The @Inject annotation directs the JavaServer Faces (JSF) CDI Container to inject (store) the object reference
//    of the ApartmentPhotoController object, after it is instantiated at runtime, into the instance variable 'apartmentPhotoController'.
//
//    We can do this because we annotated the ApartmentPhotoController class with @Named to indicate
//    that the CDI container will manage the objects instantiated from the ApartmentPhotoController class.
//     */
//    @Inject
//    private ApartmentPhotoController apartmentPhotoController;
//
//    /*
//    =========================
//    Getter and Setter Methods
//    =========================
//     */
//    // Returns the uploaded uploadedFile
//    public UploadedFile getUploadedFile() {
//        return uploadedFile;
//    }
//
//    // Obtains the uploaded uploadedFile
//    public void setUploadedFile(UploadedFile uploadedFile) {
//        this.uploadedFile = uploadedFile;
//    }
//
//    public ApartmentFacade getApartmentFacade() {
//        return apartmentFacade;
//    }
//
//    public ApartmentPhotoFacade getUserFileFacade() {
//        return apartmentPhotoFacade;
//    }
//
//    public ApartmentPhotoController getApartmentPhotoController() {
//        return apartmentPhotoController;
//    }
//
//    /*
//    ================
//    Instance Methods
//    ================
//     */
//    public String handleFileUpload(FileUploadEvent event) throws IOException {
//
//        try {
//            String user_name = (String) FacesContext.getCurrentInstance()
//                    .getExternalContext().getSessionMap().get("username");
//
//            User user = getApartmentFacade().findByUsername(user_name);
//
//            /*
//            To associate the file to the user, record "userId_filename" in the database.
//            Since each file has its own primary key (unique id), the user can upload
//            multiple files with the same name.
//             */
//            String userId_filename = user.getId() + "_" + event.getFile().getFileName();
//
//            /*
//            "The try-with-resources statement is a try statement that declares one or more resources.
//            A resource is an object that must be closed after the program is finished with it.
//            The try-with-resources statement ensures that each resource is closed at the end of the
//            statement." [Oracle]
//             */
//            try (InputStream inputStream = event.getFile().getInputstream();) {
//
//                // The method inputStreamToFile given below writes the uploaded file into the CloudStorage/FileStorage directory.
//                inputStreamToFile(inputStream, userId_filename);
//                inputStream.close();
//            }
//
//            /*
//            Create a new UserFile object with attibutes: (See UserFile table definition inputStream DB)
//                <> id = auto generated as the unique Primary key for the user file object
//                <> filename = userId_filename
//                <> user_id = user
//             */
//            UserFile newUserFile = new UserFile(userId_filename, user);
//
//            /*
//            ==============================================================
//            If the userId_filename was used before, delete the earlier file.
//            ==============================================================
//             */
//            List<UserFile> filesFound = getUserFileFacade().findByFilename(userId_filename);
//
//            /*
//            If the userId_filename already exists in the database,
//            the filesFound List will not be empty.
//             */
//            if (!filesFound.isEmpty()) {
//
//                // Remove the file with the same name from the database
//                getUserFileFacade().remove(filesFound.get(0));
//            }
//
//            //---------------------------------------------------------------
//            //
//            // Create the new UserFile entity (row) in the database
//            getApartmentPhotoFacade().create(newUserFile);
//
//            // This sets the necessary flag to ensure the messages are preserved.
//            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//
//            getApartmentPhotoController().refreshFileList();
//
//            FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
//                    "Success!", "File(s) Uploaded Successfully!");
//            FacesContext.getCurrentInstance().addMessage(null, infoMessage);
//
//        } catch (IOException e) {
//            FacesMessage fatalErrorMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
//                    "Something went wrong during file upload!", "See: " + e.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null, fatalErrorMessage);
//        }
//
//        return "/userFile/ListUserFiles?faces-redirect=true";
//    }
//
//    // Show the File Upload Page
//    public String showFileUploadPage() {
//
//        return "/userFile/UploadFile?faces-redirect=true";
//    }
//
//    public void upload() throws IOException {
//
//        if (getUploadedFile().getSize() != 0) {
//            copyFile(getUploadedFile());
//        }
//    }
//
//    /**
//     * Used to copy an uploadedFile
//     *
//     * @param file
//     * @throws IOException
//     */
//    public void copyFile(UploadedFile file) throws IOException {
//
//        // This sets the necessary flag to ensure the messages are preserved.
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//
//        try {
//            String user_name = (String) FacesContext.getCurrentInstance()
//                    .getExternalContext().getSessionMap().get("username");
//
//            User user = getApartmentFacade().findByUsername(user_name);
//
//            /*
//            To associate the file to the user, record "userId_filename" in the database.
//            Since each file has its own primary key (unique id), the user can upload
//            multiple files with the same name.
//             */
//            String userId_filename = user.getId() + "_" + file.getFileName();
//
//            /*
//            "The try-with-resources statement is a try statement that declares one or more resources.
//            A resource is an object that must be closed after the program is finished with it.
//            The try-with-resources statement ensures that each resource is closed at the end of the
//            statement." [Oracle]
//             */
//            try (InputStream inputStream = file.getInputstream();) {
//
//                // The method inputStreamToFile is given below.
//                inputStreamToFile(inputStream, userId_filename);
//                inputStream.close();
//            }
//
//        } catch (IOException e) {
//            FacesMessage fatalErrorMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,
//                    "Something went wrong during file copy!", "See: " + e.getMessage());
//            FacesContext.getCurrentInstance().addMessage(null, fatalErrorMessage);
//        }
//    }
//
//    private File inputStreamToFile(InputStream inputStream, String file_name)
//            throws IOException {
//
//        // Read the series of bytes from the input stream
//        byte[] buffer = new byte[inputStream.available()];
//        inputStream.read(buffer);
//
//        // Write the series of bytes on uploadedFile.
//        File targetFile = new File(Constants.FILES_ABSOLUTE_PATH, file_name);
//
//        OutputStream outStream;
//        outStream = new FileOutputStream(targetFile);
//        outStream.write(buffer);
//        outStream.close();
//
//        return targetFile;
//    }
//
//    public void setFileLocation(UserFile data) {
//        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//        ec.getFlash().put("data", data);
//    }
//
//    public String getFileLocation() {
//        return Constants.FILES_ABSOLUTE_PATH;
//    }
//
//    /**
//     * Used to return the file extension for a file.
//     *
//     * @param filename
//     * @return
//     */
//    public static String getExtension(String filename) {
//
//        if (filename == null) {
//            return null;
//        }
//        int extensionPos = filename.lastIndexOf('.');
//
//        int lastUnixPos = filename.lastIndexOf('/');
//        int lastWindowsPos = filename.lastIndexOf('\\');
//        int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
//        int index = lastSeparator > extensionPos ? -1 : extensionPos;
//
//        if (index == -1) {
//            return "";
//        } else {
//            return filename.substring(index + 1);
//        }
//    }

}
