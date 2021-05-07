/*
 * Created by Dishang Valotia on 2021.05.05  *
 * Copyright @ Dishang Valotia. All rights reserved. *
 */

/*
 **********************************
 *   Google Maps JavaScript API   *
 **********************************
 * You must register as Google developer and obtain an API key to use google maps api javascript
 * include in siteTemplate.xhtml:
 * <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDBztt9J2EE3Js0OKoeA6k8E8Zpj82CYV4"
        type="text/javascript"></script>
 * to get the js file.
 *
 *
 * This file provides api services of :
 * 1. Geocode reverse lookup (https://developers.google.com/maps/documentation/javascript/geolocation,
 *                         https://developers.google.com/maps/documentation/javascript/reference/geocoder
 * and
 * 2. Directions routing (https://developers.google.com/maps/documentation/javascript/reference/directions).
 *
*/

/* Global variables */
/*
Set the values of these variable while calling the functions needed on each page.
Setting the values here itself may lead to null pointer problems if the google api has not
loaded before the function is called.
 */
var gmap; // This will store the map to be displayed
var mapDirectionsDisplay; // This is used for displaying directions
var mapDirectionsService; // This is used for the service to get directions
var geocoder; // This is used for address lookup

/**
 * Get users current location as address
 * @param callback Function to be executed when location is found
 */
function getCurrentLocationAsAddress(callback) {
    if (navigator.geolocation) {
        // Call API to get current location in latitude, longitude
        navigator.geolocation.getCurrentPosition(
            (position) => {
                // SUCCESS
                // Wrap the coordinates
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                // Call another function to convert coordinates to address
                getReverseGeocode(pos, callback);
            },
            // Failed to get location
            () => {
                PF('growlWarningMessageWV').renderMessage({
                    "summary": "Failed to get your current Location",
                    "detail" : "The Geolocation service failed."});
            }
        );
    } else {
        // Browser doesn't support Geolocation
        PF('growlWarningMessageWV').renderMessage({
            "summary": "Failed to get your current Location",
            "detail" : "Your browser doesn't support geolocation."});
    }
}

/**
 * Function to convert coordinates to address
 * @param pos The latitude and longitude
 * @param callback Function to be executed when location is found
 */
function getReverseGeocode( pos , callback) {
    // API call
    geocoder.geocode({ location: pos }, (results, status) => {
        // SUCCESS
        if (status === "OK") {
            if (results[0]) {
                // At least 1 result found. Get the most accurate of all results
                callback(results[0].formatted_address);
            } else {
                // No locations found
                PF('growlWarningMessageWV').renderMessage({
                    "summary": "Failed to get your current Location",
                    "detail" : "The Geolocation service failed."});
            }
        } else {
            // API call failed
            PF('growlWarningMessageWV').renderMessage({
                "summary": "Failed to get your current Location",
                "detail" : "Geocoder failed due to: " + status});
        }
    });
}

/**
 * Initialize map to get directions
 */
function getDirections() {
    // Get the p:gmap component which has the map
    document.getElementById('ApartmentViewDirectionsForm:gmap')

    // Initialise that map
    gmap = new google.maps.Map(document.getElementById('ApartmentViewDirectionsForm:gmap'), {
        zoom: 15,
        center: {lat: 37.227264, lng: -80.420745}, // Blacksburg Center
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    // Assign the directions display component this map to draw on
    mapDirectionsDisplay.setMap(gmap);
    // Get the route and draw it.
    drawRoute();
}

function drawRoute() {

    // Travel mode is any of WALKING, BICYCLING, DRIVING, TRANSIT
    var selectedTravelMode = document.getElementById('ApartmentViewDirectionsForm:directionType').value;
    // Get the start location address
    var startLocation = document.getElementById('ApartmentViewDirectionsForm:fromAddress').value;
    // Get the end location address
    var endLocation = document.getElementById('ApartmentViewDirectionsForm:toAddress').value;

    // Create the request payload with the above 3 properties
    var request = {
            origin: startLocation,
            destination: endLocation,
            travelMode: google.maps.TravelMode[selectedTravelMode.toUpperCase()]
        };

    // Call the directions service to get the route.
    // It will return the route information as response
    mapDirectionsService.route(request, function (response, status) {
        // Route was found successfully
        if (status === google.maps.DirectionsStatus.OK) {
            // Draw the route that is present in the response
            mapDirectionsDisplay.setDirections(response);
        } else {
            // Route was not found. Throw an error message
            PF('growlErrorMessageWV').renderMessage({
                "summary": "Failed to get Directions",
                "detail" : "Please check the addresses."})
        }
    });
}


