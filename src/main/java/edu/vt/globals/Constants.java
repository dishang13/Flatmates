/*
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang, Siva. All rights reserved.
 */
package edu.vt.globals;

public final class Constants {
    /*
    ==================================================
    |   Use of Class Variables as Global Constants   |
    ==================================================
    All of the variables in this class are Class Variables (typed with the "static" keyword")
    with Constant values, which can be accessed in any class in the project by specifying
    Constants.CONSTANTNAME, i.e., ClassName.ClassVariableName

    =====================================================
    |   Our Design Decision for Use of External Files   |
    =====================================================
    We decided to use directories external to our application for the storage and retrieval of user's files.
    We do not want to store/retrieve external files into/from our database for the following reasons:

        (a) Database storage and retrieval of large files as BLOB (Binary Large OBject) degrades performance.
        (b) BLOBs increase the database complexity.
        (c) The operating system handles the external files instead of the database management system.

    WildFly provides an internal web server, named Undertow, to display external files.
    See https://docs.wildfly.org/21/Admin_Guide.html#Undertow

    ========================================================================================
    |   For storing and internally accessing external files, we use absolute file paths.   |
    ========================================================================================
    Windows:
        public static final String PHOTOS_ABSOLUTE_PATH = "C:/Users/Balci/DocRoot/BevqPhotoStorage/";
    Unix (macOS) or Linux:
     */
    // To run locally
    // Dishang's Paths
//    public static final String USER_PHOTOS_ABSOLUTE_PATH = "C:/Users/disha/OneDrive - Virginia Tech/MEngg/CS5704 Software Engineering/CloudDrive/DocRoot/Team4-FileStorage/UserPhotoStorage/";
//    public static final String APARTMENT_PHOTOS_ABSOLUTE_PATH = "C:/Users/disha/OneDrive - Virginia Tech/MEngg/CS5704 Software Engineering/CloudDrive/DocRoot/Team4-FileStorage/ApartmentPhotoStorage/";


    public static final String USER_PHOTOS_ABSOLUTE_PATH = "/Users/sivakrishna/DocRoot/Team4-FileStorage/UserPhotoStorage/";
    public static final String APARTMENT_PHOTOS_ABSOLUTE_PATH = "/Users/sivakrishna/DocRoot/Team4-FileStorage/UserPhotoStorage/";

    // Siva's Paths
    //public static final String USER_PHOTOS_ABSOLUTE_PATH = "/Users/sivakrishna/DocRoot/Team4-FileStorage/UserPhotoStorage/";
    //public static final String APARTMENT_PHOTOS_ABSOLUTE_PATH = "/Users/sivakrishna/DocRoot/Team4-FileStorage/UserPhotoStorage/";

    // To run on your AWS EC2 instance
//    public static final String USER_PHOTOS_ABSOLUTE_PATH = "/opt/wildfly/DocRoot/Team4-FileStorage/UserPhotoStorage/";
//    public static final String APARTMENT_PHOTOS_ABSOLUTE_PATH = "/opt/wildfly/DocRoot/Team4-FileStorage/ApartmentPhotoStorage/";

    /*
     ---------------------------------
     To Deploy to Your AWS EC2 server:
     ---------------------------------
     STEP 1: Comment out the constant under "To run locally" above.
     STEP 2: Uncomment the constant under "To run on your AWS EC2 instance" above.

     STEP 3: Comment out the constant under "To run locally" below.
     STEP 4: Uncomment the constant under "To run on your AWS EC2 instance with your IP address" below.
     STEP 5: Replace 54.92.194.218 with the public IP address of your AWS EC2 instance.

     STEP 6: Select Build --> Rebuild Project.
     STEP 7: Run your app to generate the WAR file. Do not use the app locally!
     STEP 8: Use the generated WAR file to deploy your app to your AWS EC2 server.

     STEP 9: Undo the above changes to run the app locally.
     */

    /*
    =================================================================================================
    |   For displaying external files to the user in an XHTML page, we use the Undertow subsystem.  |
    =================================================================================================
     We configured WildFly Undertow subsystem so that
     http://localhost:8080/bevqphotos/p displays photo file p from /Users/Balci/DocRoot/BevqPhotoStorage/
     */
    // To run locally
    public static final String USER_PHOTOS_URI = "http://localhost:8080/flatmatesuserphotos/";
    public static final String APARTMENT_PHOTOS_URI = "http://localhost:8080/flatmatesapartmentphotos/";

    // Dishang's --To run on your AWS EC2 instance with your IP address
//    public static final String USER_PHOTOS_URI = "http://3.236.179.184:8080/flatmatesuserphotos/";
//    public static final String APARTMENT_PHOTOS_URI = "http://3.236.179.184:8080/flatmatesapartmentphotos/";

    // Siva's -- To run on your AWS EC2 instance with your IP address
//    public static final String USER_PHOTOS_URI = "http://54.227.9.38:8080/flatmatesuserphotos/";
//    public static final String APARTMENT_PHOTOS_URI = "http://54.227.9.38:8080/flatmatesapartmentphotos/";

    /* 
    ==================================================
    |             Our Design Decision                |
    ==================================================
    
    We decided to scale down the user's uploaded photo to 200x200 px,
    which we call the Thumbnail photo, and use it.
    
    We do not want to use the uploaded photo as is, which may be
    very large in size degrading performance.
     */
    public static final Integer THUMBNAIL_SIZE = 200;

    /* 
    United States postal state abbreviations (codes) 
    */
    public static final String[] STATES = {"AK", "AL", "AR", "AZ", "CA", "CO", "CT",
        "DC", "DE", "FL", "GA", "GU", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA",
        "MD", "ME", "MH", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
        "NV", "NY", "OH", "OK", "OR", "PA", "PR", "PW", "RI", "SC", "SD", "TN", "TX", "UT",
        "VA", "VI", "VT", "WA", "WI", "WV", "WY"};

    /* 
    A security question is selected and answered by the user at the time of account creation.
    The selected question is used as a second level of authentication in addition to password.
    */
    public static final String[] QUESTIONS = {
        "In what city or town did your mother and father meet?",
        "In what city or town were you born?",
        "What did you want to be when you grew up?",
        "What do you remember most from your childhood?",
        "What is the name of the boy or girl that you first kissed?",
        "What is the name of the first school you attended?",
        "What is the name of your favorite childhood friend?",
        "What is the name of your first pet?",
        "What is your mother's maiden name?",
        "What was your favorite place to visit as a child?"
    };

    /*
    Eating Preference option
    */
    public static final String[] EATING_PREF = {
        "No Preference",
        "Non-Vegetarian",
        "Vegetarian",
        "Vegan"
    };

    /*
    Smoking preference options
    */
    public static final String[] SMOKING_PREF = {
        "No Preference",
        "Smoking",
        "Non-Smoking"
    };

    /*
    Alcohol drinking preference options
    */
    public static final String[] ALCOHOL_PREF = {
        "No Preference",
        "Alcohol Drinker",
        "Alcohol Non-Drinker",
    };

    /*
    Sleeping preference options
    */
    public static final String[] SLEEPING_PREF = {
        "Flexible",
        "Morning Person",
        "Night Person"
    };

    /*
    Social Life preference options
    */
    public static final String[] SOCIAL_PREF = {
        "No Preference",
        "Party-goer",
        "Quiet",
    };
}
