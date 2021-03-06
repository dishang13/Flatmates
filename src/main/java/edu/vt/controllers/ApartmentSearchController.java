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
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
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
@Named("apartmentSearchController")

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
public class ApartmentSearchController implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    private List<Apartment> searchResults;

    // Search filter parameters
    private String name;
    private String minBeds;
    private String maxBeds;
    private String numBaths;
    private String minRent;
    private String maxRent;
    private Date startDate;
    private Date endDate;

    private Apartment selected;

    private MapModel mapModel;

    private String fromAddress;
    private String toAddress;
    private String directionType;

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
    public ApartmentSearchController() { }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */

    public List<Apartment> getSearchResults() {
        if(searchResults == null) {
            searchResults = apartmentFacade.searchApartments(processSearchFilters());
        }
        return searchResults;
    }

    public void setSearchResults(List<Apartment> searchResults) {
        this.searchResults = searchResults;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinBeds() {
        return minBeds;
    }

    public void setMinBeds(String minBeds) {
        this.minBeds = minBeds;
    }

    public String getMaxBeds() {
        return maxBeds;
    }

    public void setMaxBeds(String maxBeds) {
        this.maxBeds = maxBeds;
    }

    public String getNumBaths() {
        return numBaths;
    }

    public void setNumBaths(String numBaths) {
        this.numBaths = numBaths;
    }

    public String getMinRent() {
        return minRent;
    }

    public void setMinRent(String minRent) {
        this.minRent = minRent;
    }

    public String getMaxRent() {
        return maxRent;
    }

    public void setMaxRent(String maxRent) {
        this.maxRent = maxRent;
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

    public Apartment getSelected() {
        return selected;
    }

    public void setSelected(Apartment selected) {
        // We need to update mapModel everytime selected changes
        this.mapModel = null;
        this.selected = selected;
        if(selected != null) {
            this.fromAddress = selected.getAddress();
            this.toAddress = selected.getAddress();
        }
//        mapModel = getMapModel();
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

    public MapModel getMapModel() {
        if(mapModel == null && selected != null) {
            mapModel = new DefaultMapModel();
            mapModel.addOverlay(new Marker(new LatLng(selected.getLatitude().doubleValue(), selected.getLongitude().doubleValue())));
        }
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getDirectionType() {
        return directionType;
    }

    public void setDirectionType(String directionType) {
        this.directionType = directionType;
    }

    /* Get center for the gmap.
    *  If selected != null use that as center
    *  Other wise use Blacksburg as center
    */
    public String getCenter() {
        if(selected != null) {
            return selected.getLatLong();
        }
        if(searchResults.size() == 1) {
            return searchResults.get(0).getLatLong();
        }
        // Get Blacksburg location
        return "37.2296, -80.4139";
    }

    public void onMarkerSelect(OverlaySelectEvent event) {
        Marker marker = (Marker) event.getOverlay();
//TODO
// && marker.getLatlng().toString().equals(a.getLatLong())
        for(Apartment a: searchResults) {
            if(a.getName().equals(marker.getTitle())) {
                System.out.println(a.getName());
                setSelected(a);
                break;
            }
        }
    }

    public String directions(String type){
        System.out.println(type);
        directionType = type;
//        mapModel = null;
        return "/searchApartment/Directions?faces-redirect=true";
    }

    /*
        ================
        Instance Methods
        ================
         */
    public void unselect() {
        selected = null;
        searchResults = null;
    }

    private String processSearchFilters() {
        String searchFilters = "SELECT a FROM Apartment a WHERE a.archived = false";

        if(name != null && !name.isBlank()) {
            searchFilters += " AND a.name LIKE '%" + name.replaceAll("[\"']", "") + "%'";
        }
        if(minBeds != null && !minBeds.isBlank()) {
            searchFilters += " AND a.numBed >=" + minBeds;
        }
        if(maxBeds != null && !maxBeds.isBlank()) {
            searchFilters += " AND a.numBed <=" + maxBeds;
        }
        if(numBaths != null && !numBaths.isBlank()) {
            searchFilters += " AND a.numBath >=" + numBaths;
        }
        if(minRent != null && !minRent.isBlank()) {
            searchFilters += " AND a.rent >=" + minRent;
        }
        if(maxRent != null && !maxRent.isBlank()) {
            searchFilters += " AND a.rent <=" + maxRent;
        }
        if(startDate != null) {
            searchFilters += " AND a.start_date <=" + startDate.toString();
        }
        if(endDate != null) {
            searchFilters += " AND a.end_date <=" + endDate.toString();
        }
        System.out.println(searchFilters);
        return searchFilters;
    }

    /*
     ***********************************
     *   Display the List.xhtml Page   *
     ***********************************
     */
    public String search() {
        // Unselect previously selected public video if any before showing the search results
        selected = null;

        // Invalidate list of search items to trigger re-query.
        searchResults = null;

        return "/searchApartment/List?faces-redirect=true";
    }

    /*
    Clear the search dialog.
     */
    public void clear() {
        name = "";
        numBaths = "";
        minBeds = "";
        maxBeds = "";
        minRent = "";
        maxRent = "";
        startDate = null;
        endDate = null;
        searchResults = null;
        selected = null;
    }

    /*
     ******************************************
     *   Converter Method for Data Exporter   *
     ******************************************

    PrimeFaces p:dataExporter requires the values to be exported to be of String type.
    This method is called in searchApartments/List.xhtml.
     */
    public String convertIntToString(Integer value) {
        return Integer.toString(value);
    }
}
