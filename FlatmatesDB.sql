# ----------------------------------------------------
# SQL script to create the tables for the
# Flatmates Database (FlatmatesDB)
# Created by Dishang R Valotia, SivaKrishna Vattikonda
# ----------------------------------------------------

/*
Tables to be dropped must be listed in a logical order based on dependency.
ApartmentPhoto depends on Apartment. Therefore, they must be dropped before Apartment.
Apartment and UserPhoto depends on User. Therefore, they must be dropped before User.
*/
DROP TABLE IF EXISTS ApartmentPhoto, Apartment, UserPhoto, User;

/* The User table contains attributes of interest of a User. */
CREATE TABLE User (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username varchar(32) NOT NULL UNIQUE,
    password varchar(256) NOT NULL,  /* To store Salted and Hashed Password Parts */
    first_name varchar(32) NOT NULL,
    middle_name varchar(32),
    last_name varchar(32) NOT NULL,
    address1 varchar(128) NOT NULL,
    address2 varchar(128),
    city varchar(64) NOT NULL,
    state varchar(2) NOT NULL,
    zipcode varchar(10) NOT NULL,    /* e.g., 24060-1804 */
    security_question_number INT NOT NULL,  /* Refers to the number of the selected security question */
    security_answer varchar(128) NOT NULL,
    email varchar(128) NOT NULL,
    phone varchar(20),
    eating_pref_number INT NOT NULL,
    smoking_pref_number INT NOT NULL,
    alcohol_pref_number INT NOT NULL,
    sleep_pref_number INT NOT NULL,
    social_pref_number INT NOT NULL,
    PRIMARY KEY (id)
);

/* The UserPhoto table contains attributes of interest of a user's photo. */
CREATE TABLE UserPhoto (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    extension ENUM('jpeg', 'jpg', 'png', 'gif') NOT NULL,
    user_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/* The User table contains attributes of interest of an Apartment. */
CREATE TABLE Apartment (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name varchar(256) NOT NULL,
    description varchar(1000),
    date_entered DATE NOT NULL, /* YYYY-MM-DD */
    user_id INT UNSIGNED NOT NULL,
    archived BOOLEAN NOT NULL DEFAULT false,
    address varchar(500) NOT NULL,
    num_bed INT NOT NULL,
    num_bath INT NOT NULL,
    rent INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    latitude DECIMAL(8,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL,
    complex_website varchar(1028),
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE
);

/* The UserPhoto table contains attributes of interest of an apartment's photo. */
CREATE TABLE ApartmentPhoto (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    filename VARCHAR(256) NOT NULL,
    apartment_id INT UNSIGNED,
    FOREIGN KEY (apartment_id) REFERENCES Apartment(id) ON DELETE CASCADE
);