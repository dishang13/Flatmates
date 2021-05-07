/*
 * Created by Osman Balci on 2017.10.23  *
 * Copyright Â© 2017 Osman Balci. All rights reserved. *
 */

/*********************************************************************************************
 You must provide your Google Maps JavaScript API Developer Key to able to show maps and
 get directions. I specified mine in siteTemplate.xhtml as follows:

 <script src="https://maps.google.com/maps/api/js?key=AIzaSyDUMWJdnV_fekj5InNCV82_hBq_gKWG8lw"
 type="text/javascript"></script>

 This is given in siteTemplate.xhtml so that each page can use Google Maps JavaScript API.

 This file provides the JavaScript functions we need to display Google maps and get directions.
 *********************************************************************************************/

/* Global variables */

var google;

// Object reference 'gmap' to point to a Google Map object
var gmap;

// Object reference 'currentMarker' to point to a VT building location on gmap
var currentMarker = null;

var geocoder = new google.maps.Geocoder();

/*
 You can obtain directions via driving, bicycling, bus, or walking by using the DirectionsService object.
 This object communicates with the Google Maps API Directions Service which receives direction requests
 and returns computed results. You can handle these directions results by using the DirectionsRenderer
 object to render these results. [https://developers.google.com/maps/documentation/javascript/directions]
 */

// Instantiate a DirectionsService object and store its object reference into directionsService.
var directionsService = new google.maps.DirectionsService();

// Instantiate a DirectionsRenderer object and store its object reference into directionsDisplay.
var directionsDisplay = new google.maps.DirectionsRenderer();

// Create and display a Virginia Tech campus gmap
function initializeMap() {
    console.log('intMap');
    /*
     Instantiate a new Virginia Tech campus gmap object and set its properties.
     document.getElementById('gmap') --> Obtains the Google Map style definition
     from the div element with id="gmap" in ShowOnMap.xhtml
     */

    document.getElementById('ApartmentViewDirectionsForm:gmap')
    console.log('document.getElementById');
    gmap = new google.maps.Map(document.getElementById('ApartmentViewDirectionsForm:gmap'), {
        zoom: 15,
        center: {lat: 37.227264, lng: -80.420745},
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });
    console.log('new google.maps');
    // Show the desired gmap using the gmap created above by calling the display() function.
    display();

}

/*
 The Virginia Tech campus gmap created in the initializeMap() function above is used to show:
 (1) directions from one building to another on campus,
 (2) the location of a single VT building, or
 (3) locations of VT buildings in a given building category.
 */
function display() {
    /*
     document.getElementById("destinationName").value --> Obtains the name of the destination
     VT building from the hidden input element with id="destinationName" in ShowOnMap.xhtml
     */
    if (document.getElementById("ApartmentViewDirectionsForm:toAddress").value !== '') {
        /*
         If destinationName has a value, the user asked for directions.
         Show directions on the VT campus gmap created in the initializeMap() function.
         */
        drawRoute();
    }
}

function drawRoute() {

    directionsDisplay.setMap(gmap);

    var selectedTravelMode = document.getElementById('ApartmentViewDirectionsForm:directionType').value;
    var startLocation = document.getElementById('ApartmentViewDirectionsForm:fromAddress').value;
    var endLocation = document.getElementById('ApartmentViewDirectionsForm:toAddress').value;
    /***************************** Directions Request Object Creation ******************************/

        // Create a DirectionsRequest object named 'request' with the following properties as key:value pairs
    var request = {
            origin: startLocation,
            destination: endLocation,
            travelMode: google.maps.TravelMode[selectedTravelMode.toUpperCase()]
        };

   directionsService.route(request, function (response, status) {

        // The operator === tests for equal value and equal type
        if (status === google.maps.DirectionsStatus.OK) {

            // If DirectionsStatus is okay, then display the route for the directions on gmap
            directionsDisplay.setDirections(response);
        } else {
            PF('growlErrorMessageWV').renderMessage({
                "summary": "Failed to get Directions",
                "detail" : "Please check the addresses."})
        }
    });
}

function getCurrentLocationAsAddress(callback) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                console.log(JSON.stringify(pos));
                getReverseGeocode(pos, callback);
            },
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

function getReverseGeocode( pos , callback) {
    geocoder.geocode({ location: pos }, (results, status) => {
        if (status === "OK") {
            if (results[0]) {
                console.log(results[0].formatted_address);
                callback(results[0].formatted_address);
            } else {
                window.alert("No results found");
            }
        } else {
            window.alert("Geocoder failed due to: " + status);
        }
    });
}


