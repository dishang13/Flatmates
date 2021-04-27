/*
 * Created by Osman Balci on 2021.2.27
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.managers;

import edu.vt.pojo.Category;
import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.UserFacade;
import edu.vt.globals.Methods;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import edu.vt.controllers.UserQuestionnaireController;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.json.JSONArray;

@Named(value = "questionnaireManager")
@SessionScoped

public class QuestionnaireManager implements Serializable {

    /*
    ==================
    Instance Variables
    ==================
     */
    // Inject (store) the UserFacade object reference into userFacade after it is instantiated at runtime
    @EJB
    private UserFacade userFacade;

    @Inject
    private UserQuestionnaireController userQuestionnaireController;

    // 'items' is a List containing the object references of Category objects
    private List<Category> items = null;

    private String category1HowOften;
    private String category1HowMuch;

    private String category2HowOften;
    private String category2HowMuch;

    private String category3HowOften;
    private String category3HowMuch;

    private String category4HowOften;
    private String category4HowMuch;

    private String category5HowOften;
    private String category5HowMuch;

    private String category6HowOften;
    private String category6HowMuch;
    private String category6option;

    private String category7HowOften;
    private String category7HowMuch;

    private String category8HowOften;
    private String category8HowMuch;

    private String category9HowOften;
    private String category9HowMuch;

    private String category10HowOften;
    private String category10HowMuch;

    private String category11HowOften;
    private String category11HowMuch;
    private String category11option;

    private String category12HowOften;
    private String category12HowMuch;
    private String category12option1;
    private String category12option2;
    private String category12option3;

    private String category13HowOften;
    private String category13HowMuch;

    private String category14HowOften;
    private String category14HowMuch;

    private String category15HowOften;
    private String category15HowMuch;

    private String other1HowOften;
    private String other1HowMuch;
    private String other2HowOften;
    private String other2HowMuch;
    private String other3HowOften;
    private String other3HowMuch;
    private String other6HowOften;
    private String other6HowMuch;
    private String other7HowOften;
    private String other7HowMuch;
    private String other13HowOften;
    private String other13HowMuch;
    private String other15HowOften;
    private String other15HowMuch;

    /*
    ==================
    Constructor Method
    ==================
     */
    public QuestionnaireManager() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public UserFacade getUserFacade() {
        return userFacade;
    }

    public UserQuestionnaireController getUserQuestionnaireController() {
        return userQuestionnaireController;
    }

    public List<Category> getItems() {
        return items;
    }

    public void setItems(List<Category> items) {
        this.items = items;
    }

    public String getCategory1HowOften() {
        return category1HowOften;
    }

    public void setCategory1HowOften(String category1HowOften) {
        this.category1HowOften = category1HowOften;
    }

    public String getCategory1HowMuch() {
        return category1HowMuch;
    }

    public void setCategory1HowMuch(String category1HowMuch) {
        this.category1HowMuch = category1HowMuch;
    }

    public String getCategory2HowOften() {
        return category2HowOften;
    }

    public void setCategory2HowOften(String category2HowOften) {
        this.category2HowOften = category2HowOften;
    }

    public String getCategory2HowMuch() {
        return category2HowMuch;
    }

    public void setCategory2HowMuch(String category2HowMuch) {
        this.category2HowMuch = category2HowMuch;
    }

    public String getCategory3HowOften() {
        return category3HowOften;
    }

    public void setCategory3HowOften(String category3HowOften) {
        this.category3HowOften = category3HowOften;
    }

    public String getCategory3HowMuch() {
        return category3HowMuch;
    }

    public void setCategory3HowMuch(String category3HowMuch) {
        this.category3HowMuch = category3HowMuch;
    }

    public String getCategory4HowOften() {
        return category4HowOften;
    }

    public void setCategory4HowOften(String category4HowOften) {
        this.category4HowOften = category4HowOften;
    }

    public String getCategory4HowMuch() {
        return category4HowMuch;
    }

    public void setCategory4HowMuch(String category4HowMuch) {
        this.category4HowMuch = category4HowMuch;
    }

    public String getCategory5HowOften() {
        return category5HowOften;
    }

    public void setCategory5HowOften(String category5HowOften) {
        this.category5HowOften = category5HowOften;
    }

    public String getCategory5HowMuch() {
        return category5HowMuch;
    }

    public void setCategory5HowMuch(String category5HowMuch) {
        this.category5HowMuch = category5HowMuch;
    }

    public String getCategory6HowOften() {
        return category6HowOften;
    }

    public void setCategory6HowOften(String category6HowOften) {
        this.category6HowOften = category6HowOften;
    }

    public String getCategory6HowMuch() {
        return category6HowMuch;
    }

    public void setCategory6HowMuch(String category6HowMuch) {
        this.category6HowMuch = category6HowMuch;
    }

    public String getCategory6option() {
        return category6option;
    }

    public void setCategory6option(String category6option) {
        this.category6option = category6option;
    }

    public String getCategory7HowOften() {
        return category7HowOften;
    }

    public void setCategory7HowOften(String category7HowOften) {
        this.category7HowOften = category7HowOften;
    }

    public String getCategory7HowMuch() {
        return category7HowMuch;
    }

    public void setCategory7HowMuch(String category7HowMuch) {
        this.category7HowMuch = category7HowMuch;
    }

    public String getCategory8HowOften() {
        return category8HowOften;
    }

    public void setCategory8HowOften(String category8HowOften) {
        this.category8HowOften = category8HowOften;
    }

    public String getCategory8HowMuch() {
        return category8HowMuch;
    }

    public void setCategory8HowMuch(String category8HowMuch) {
        this.category8HowMuch = category8HowMuch;
    }

    public String getCategory9HowOften() {
        return category9HowOften;
    }

    public void setCategory9HowOften(String category9HowOften) {
        this.category9HowOften = category9HowOften;
    }

    public String getCategory9HowMuch() {
        return category9HowMuch;
    }

    public void setCategory9HowMuch(String category9HowMuch) {
        this.category9HowMuch = category9HowMuch;
    }

    public String getCategory10HowOften() {
        return category10HowOften;
    }

    public void setCategory10HowOften(String category10HowOften) {
        this.category10HowOften = category10HowOften;
    }

    public String getCategory10HowMuch() {
        return category10HowMuch;
    }

    public void setCategory10HowMuch(String category10HowMuch) {
        this.category10HowMuch = category10HowMuch;
    }

    public String getCategory11HowOften() {
        return category11HowOften;
    }

    public void setCategory11HowOften(String category11HowOften) {
        this.category11HowOften = category11HowOften;
    }

    public String getCategory11HowMuch() {
        return category11HowMuch;
    }

    public void setCategory11HowMuch(String category11HowMuch) {
        this.category11HowMuch = category11HowMuch;
    }

    public String getCategory11option() {
        return category11option;
    }

    public void setCategory11option(String category11option) {
        this.category11option = category11option;
    }

    public String getCategory12HowOften() {
        return category12HowOften;
    }

    public void setCategory12HowOften(String category12HowOften) {
        this.category12HowOften = category12HowOften;
    }

    public String getCategory12HowMuch() {
        return category12HowMuch;
    }

    public void setCategory12HowMuch(String category12HowMuch) {
        this.category12HowMuch = category12HowMuch;
    }

    public String getCategory12option1() {
        return category12option1;
    }

    public void setCategory12option1(String category12option1) {
        this.category12option1 = category12option1;
    }

    public String getCategory12option2() {
        return category12option2;
    }

    public void setCategory12option2(String category12option2) {
        this.category12option2 = category12option2;
    }

    public String getCategory12option3() {
        return category12option3;
    }

    public void setCategory12option3(String category12option3) {
        this.category12option3 = category12option3;
    }

    public String getCategory13HowOften() {
        return category13HowOften;
    }

    public void setCategory13HowOften(String category13HowOften) {
        this.category13HowOften = category13HowOften;
    }

    public String getCategory13HowMuch() {
        return category13HowMuch;
    }

    public void setCategory13HowMuch(String category13HowMuch) {
        this.category13HowMuch = category13HowMuch;
    }

    public String getCategory14HowOften() {
        return category14HowOften;
    }

    public void setCategory14HowOften(String category14HowOften) {
        this.category14HowOften = category14HowOften;
    }

    public String getCategory14HowMuch() {
        return category14HowMuch;
    }

    public void setCategory14HowMuch(String category14HowMuch) {
        this.category14HowMuch = category14HowMuch;
    }

    public String getCategory15HowOften() {
        return category15HowOften;
    }

    public void setCategory15HowOften(String category15HowOften) {
        this.category15HowOften = category15HowOften;
    }

    public String getCategory15HowMuch() {
        return category15HowMuch;
    }

    public void setCategory15HowMuch(String category15HowMuch) {
        this.category15HowMuch = category15HowMuch;
    }

    public String getOther1HowOften() {
        return other1HowOften;
    }

    public void setOther1HowOften(String other1HowOften) {
        this.other1HowOften = other1HowOften;
    }

    public String getOther1HowMuch() {
        return other1HowMuch;
    }

    public void setOther1HowMuch(String other1HowMuch) {
        this.other1HowMuch = other1HowMuch;
    }

    public String getOther2HowOften() {
        return other2HowOften;
    }

    public void setOther2HowOften(String other2HowOften) {
        this.other2HowOften = other2HowOften;
    }

    public String getOther2HowMuch() {
        return other2HowMuch;
    }

    public void setOther2HowMuch(String other2HowMuch) {
        this.other2HowMuch = other2HowMuch;
    }

    public String getOther3HowOften() {
        return other3HowOften;
    }

    public void setOther3HowOften(String other3HowOften) {
        this.other3HowOften = other3HowOften;
    }

    public String getOther3HowMuch() {
        return other3HowMuch;
    }

    public void setOther3HowMuch(String other3HowMuch) {
        this.other3HowMuch = other3HowMuch;
    }

    public String getOther6HowOften() {
        return other6HowOften;
    }

    public void setOther6HowOften(String other6HowOften) {
        this.other6HowOften = other6HowOften;
    }

    public String getOther6HowMuch() {
        return other6HowMuch;
    }

    public void setOther6HowMuch(String other6HowMuch) {
        this.other6HowMuch = other6HowMuch;
    }

    public String getOther7HowOften() {
        return other7HowOften;
    }

    public void setOther7HowOften(String other7HowOften) {
        this.other7HowOften = other7HowOften;
    }

    public String getOther7HowMuch() {
        return other7HowMuch;
    }

    public void setOther7HowMuch(String other7HowMuch) {
        this.other7HowMuch = other7HowMuch;
    }

    public String getOther13HowOften() {
        return other13HowOften;
    }

    public void setOther13HowOften(String other13HowOften) {
        this.other13HowOften = other13HowOften;
    }

    public String getOther13HowMuch() {
        return other13HowMuch;
    }

    public void setOther13HowMuch(String other13HowMuch) {
        this.other13HowMuch = other13HowMuch;
    }

    public String getOther15HowOften() {
        return other15HowOften;
    }

    public void setOther15HowOften(String other15HowOften) {
        this.other15HowOften = other15HowOften;
    }

    public String getOther15HowMuch() {
        return other15HowMuch;
    }

    public void setOther15HowMuch(String other15HowMuch) {
        this.other15HowMuch = other15HowMuch;
    }

    /*
    ================
    Instance Methods
    ================

    ***********************************
    Process the Submitted Questionnaire
    ***********************************
     */
    public String processQuestionnaire() {

        // Instantiate a new 'items' List to contain the object references of the Category objects
        items = new ArrayList<>();

        Integer categoryNumber;
        String categoryName;
        String howOftenTitle;
        Double frequencyPerDay;
        String howMuchTitle;
        Double fluidOuncePerDay;
        String optionsOther;
        Double averageDailyConsumptionInFluidOunce;
        Double caloricDensityInKcalPerFluidOunce;
        Double averageDailyCaloricIntakeInKcal;
        Double frequencyPerDayOther;
        Double fluidOuncePerDayOther;
        Double dailyAverageFluidOunce;
        Double dailyAverageKcal;
        Double totalDailyAverageFluidOunce = 0.0;
        Double totalDailyAverageKcal = 0.0;

        // Format to show only 3 decimal places
        DecimalFormat df = new DecimalFormat("#.###");

        /* 
         ******************
         *   Category 1   *
         ******************
         */
        categoryNumber = 1;
        categoryName = "Water or Unsweetened Sparkling Water";

        frequencyPerDay = Double.parseDouble(category1HowOften);
        howOftenTitle = howOftenTitle(category1HowOften);
        frequencyPerDayOther = Double.parseDouble(other1HowOften);

        fluidOuncePerDay = Double.parseDouble(category1HowMuch);
        howMuchTitle = howMuchTitle(category1HowMuch);
        fluidOuncePerDayOther = Double.parseDouble(other1HowMuch);

        dailyAverageFluidOunce = (frequencyPerDay * fluidOuncePerDay) + (frequencyPerDayOther * fluidOuncePerDayOther);
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 0.0;
        averageDailyCaloricIntakeInKcal = 0.0;

        optionsOther = "Club Soda, Tonic Water: " + howOftenTitle(other1HowOften) + " each time " + howMuchTitle(other1HowMuch);

        Category category1 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category1);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 2   *
         ******************
         */
        categoryNumber = 2;
        categoryName = "100% Fruit Juice";

        frequencyPerDay = Double.parseDouble(category2HowOften);
        howOftenTitle = howOftenTitle(category2HowOften);
        frequencyPerDayOther = Double.parseDouble(other2HowOften);

        fluidOuncePerDay = Double.parseDouble(category2HowMuch);
        howMuchTitle = howMuchTitle(category2HowMuch);
        fluidOuncePerDayOther = Double.parseDouble(other2HowMuch);

        dailyAverageFluidOunce = (frequencyPerDay * fluidOuncePerDay) + (frequencyPerDayOther * fluidOuncePerDayOther);
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 17.67;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "100% Vegetable Juice, Unsweetened Coconut Water: " + howOftenTitle(other2HowOften) + " each time " + howMuchTitle(other2HowMuch);

        Category category2 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category2);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 3   *
         ******************
         */
        categoryNumber = 3;
        categoryName = "Sweetened Juice Beverage/Drink (fruit punch, juice cocktail, Sunny Delight, Capri Sun)";

        frequencyPerDay = Double.parseDouble(category3HowOften);
        howOftenTitle = howOftenTitle(category3HowOften);
        frequencyPerDayOther = Double.parseDouble(other3HowOften);

        fluidOuncePerDay = Double.parseDouble(category3HowMuch);
        howMuchTitle = howMuchTitle(category3HowMuch);
        fluidOuncePerDayOther = Double.parseDouble(other3HowMuch);

        dailyAverageFluidOunce = (frequencyPerDay * fluidOuncePerDay) + (frequencyPerDayOther * fluidOuncePerDayOther);
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 14.3;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "Lemonade, Limeade, Sweetened Coconut Water: " + howOftenTitle(other3HowOften) + " each time " + howMuchTitle(other3HowMuch);

        Category category3 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category3);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 4   *
         ******************
         */
        categoryNumber = 4;
        categoryName = "Whole Milk, Reduced Fat Milk 2%, or Chocolate Milk";

        frequencyPerDay = Double.parseDouble(category4HowOften);
        howOftenTitle = howOftenTitle(category4HowOften);

        fluidOuncePerDay = Double.parseDouble(category4HowMuch);
        howMuchTitle = howMuchTitle(category4HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 19.8;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "";

        Category category4 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category4);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 5   *
         ******************
         */
        categoryNumber = 5;
        categoryName = "Low Fat Milk 1%, Fat Free / Skim Milk, Buttermilk or Soy Milk";

        frequencyPerDay = Double.parseDouble(category5HowOften);
        howOftenTitle = howOftenTitle(category5HowOften);

        fluidOuncePerDay = Double.parseDouble(category5HowMuch);
        howMuchTitle = howMuchTitle(category5HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 12.1;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "";

        Category category5 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category5);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 6   *
         ******************
         */
        categoryNumber = 6;
        categoryName = "Nut Milk (almond, cashew, coconut)";

        frequencyPerDay = Double.parseDouble(category6HowOften);
        howOftenTitle = howOftenTitle(category6HowOften);
        frequencyPerDayOther = Double.parseDouble(other6HowOften);

        fluidOuncePerDay = Double.parseDouble(category6HowMuch);
        howMuchTitle = howMuchTitle(category6HowMuch);
        fluidOuncePerDayOther = Double.parseDouble(other6HowMuch);

        dailyAverageFluidOunce = (frequencyPerDay * fluidOuncePerDay) + (frequencyPerDayOther * fluidOuncePerDayOther);
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = Double.parseDouble(category6option);

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        switch (category6option) {
            case "0":
                optionsOther = "I do not drink it (0)";
                break;
            case "4.2":
                optionsOther = "Unsweetened (4.2)";
                break;
            case "9.8":
                optionsOther = "Flavored, Original or Plain (9.8)";
                break;
            default:
                System.out.print("category6option is out of range!");
                break;
        }

        String other = " / Ripple (legume-based milk), Kefir: " + howOftenTitle(other6HowOften) + " each time " + howMuchTitle(other6HowMuch);

        optionsOther = optionsOther + other;

        Category category6 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category6);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 7   *
         ******************
         */
        categoryNumber = 7;
        categoryName = "Soft Drinks, Regular ";

        frequencyPerDay = Double.parseDouble(category7HowOften);
        howOftenTitle = howOftenTitle(category7HowOften);
        frequencyPerDayOther = Double.parseDouble(other7HowOften);

        fluidOuncePerDay = Double.parseDouble(category7HowMuch);
        howMuchTitle = howMuchTitle(category7HowMuch);
        fluidOuncePerDayOther = Double.parseDouble(other7HowMuch);

        dailyAverageFluidOunce = (frequencyPerDay * fluidOuncePerDay) + (frequencyPerDayOther * fluidOuncePerDayOther);
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 13.3;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "Root Beer, Cream Soda, Kombucha: " + howOftenTitle(other7HowOften) + " each time " + howMuchTitle(other7HowMuch);

        Category category7 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category7);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 8   *
         ******************
         */
        categoryNumber = 8;
        categoryName = "Energy and Sports Drinks, Regular (Red Bull, Gatorade, Powerade)";

        frequencyPerDay = Double.parseDouble(category8HowOften);
        howOftenTitle = howOftenTitle(category8HowOften);

        fluidOuncePerDay = Double.parseDouble(category8HowMuch);
        howMuchTitle = howMuchTitle(category8HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 14.0;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "";

        Category category8 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category8);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         ******************
         *   Category 9   *
         ******************
         */
        categoryNumber = 9;
        categoryName = "Diet or Artificially Sweetened Soft Drinks, Energy and Sports Drinks (Diet Coke, Crystal Light, artificially sweetened sparkling water, Sugar-Free or Total Zero Red Bull, Powerade Zero)";

        frequencyPerDay = Double.parseDouble(category9HowOften);
        howOftenTitle = howOftenTitle(category9HowOften);

        fluidOuncePerDay = Double.parseDouble(category9HowMuch);
        howMuchTitle = howMuchTitle(category9HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 0.3;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "";

        Category category9 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category9);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         *******************
         *   Category 10   *
         *******************
         */
        categoryNumber = 10;
        categoryName = "Sweet Tea (with sugar)";

        frequencyPerDay = Double.parseDouble(category10HowOften);
        howOftenTitle = howOftenTitle(category10HowOften);

        fluidOuncePerDay = Double.parseDouble(category10HowMuch);
        howMuchTitle = howMuchTitle(category10HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 10.0;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "";

        Category category10 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category10);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         *******************
         *   Category 11   *
         *******************
         */
        categoryNumber = 11;
        categoryName = "Tea or Coffee, black (no creamer or milk)";

        frequencyPerDay = Double.parseDouble(category11HowOften);
        howOftenTitle = howOftenTitle(category11HowOften);

        fluidOuncePerDay = Double.parseDouble(category11HowMuch);
        howMuchTitle = howMuchTitle(category11HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = Double.parseDouble(category11option);

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        switch (category11option) {
            case "0":
                optionsOther = "With No Sweetener (0)";
                break;
            case "0.7":
                optionsOther = "With Artificial Sweetener (0.7)";
                break;
            case "5":
                optionsOther = "With Sugar (5)";
                break;
            default:
                System.out.print("category11option is out of range!");
                break;
        }

        Category category11 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category11);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         *******************
         *   Category 12   *
         *******************
         */
        categoryNumber = 12;
        categoryName = "Tea or Coffee with sweetener, milk and/or creamer";

        frequencyPerDay = Double.parseDouble(category12HowOften);
        howOftenTitle = howOftenTitle(category12HowOften);

        fluidOuncePerDay = Double.parseDouble(category12HowMuch);
        howMuchTitle = howMuchTitle(category12HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = Double.parseDouble(category12option1) + Double.parseDouble(category12option2) + Double.parseDouble(category12option3);

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        String option1 = "";
        String option2 = "";
        String option3 = "";

        switch (category12option1) {
            case "0":
                option1 = "With No Sweetener (0)";
                break;
            case "0.7":
                option1 = "With Artificial Sweetener (0.7)";
                break;
            case "5":
                option1 = "With Sugar (5)";
                break;
            default:
                System.out.print("category12option1 is out of range!");
                break;
        }

        switch (category12option2) {
            case "0":
                option2 = "With No Milk (0)";
                break;
            case "2.5":
                option2 = "With Half and Half (2.5)";
                break;
            case "0.717":
                option2 = "With Milk (0.717)";
                break;
            default:
                System.out.print("category12option2 is out of range!");
                break;
        }

        switch (category12option3) {
            case "0":
                option3 = "With No Creamer (0)";
                break;
            case "3.375":
                option3 = "With Sugar-Free Creamer (3.375)";
                break;
            case "3.3125":
                option3 = "With Plain Creamer (3.3125)";
                break;
            case "6.125":
                option3 = "With Flavored Creamer (6.125)";
                break;
            default:
                System.out.print("category12option3 is out of range!");
                break;
        }

        optionsOther = option1 + " | " + option2 + " | " + option3;

        Category category12 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category12);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         *******************
         *   Category 13   *
         *******************
         */
        categoryNumber = 13;
        categoryName = "Wine (red or white)";

        frequencyPerDay = Double.parseDouble(category13HowOften);
        howOftenTitle = howOftenTitle(category13HowOften);
        frequencyPerDayOther = Double.parseDouble(other13HowOften);

        fluidOuncePerDay = Double.parseDouble(category13HowMuch);
        howMuchTitle = howMuchTitle(category13HowMuch);
        fluidOuncePerDayOther = Double.parseDouble(other13HowMuch);

        dailyAverageFluidOunce = (frequencyPerDay * fluidOuncePerDay) + (frequencyPerDayOther * fluidOuncePerDayOther);
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 20.6;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "Champagne, Sparkling wine, Sangria: " + howOftenTitle(other13HowOften) + " each time " + howMuchTitle(other13HowMuch);

        Category category13 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category13);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         *******************
         *   Category 14   *
         *******************
         */
        categoryNumber = 14;
        categoryName = "Hard Liquor (vodka, rum, tequila, etc.)";

        frequencyPerDay = Double.parseDouble(category14HowOften);
        howOftenTitle = howOftenTitle(category14HowOften);

        fluidOuncePerDay = Double.parseDouble(category14HowMuch);
        howMuchTitle = howMuchTitle(category14HowMuch);

        dailyAverageFluidOunce = frequencyPerDay * fluidOuncePerDay;
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 68.18;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "";

        Category category14 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category14);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         *******************
         *   Category 15   *
         *******************
         */
        categoryNumber = 15;
        categoryName = "Beer, Ale, Wine Cooler, Non-alcoholic or Light Beer";

        frequencyPerDay = Double.parseDouble(category15HowOften);
        howOftenTitle = howOftenTitle(category15HowOften);
        frequencyPerDayOther = Double.parseDouble(other15HowOften);

        fluidOuncePerDay = Double.parseDouble(category15HowMuch);
        howMuchTitle = howMuchTitle(category15HowMuch);
        fluidOuncePerDayOther = Double.parseDouble(other15HowMuch);

        dailyAverageFluidOunce = (frequencyPerDay * fluidOuncePerDay) + (frequencyPerDayOther * fluidOuncePerDayOther);
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 10.3;

        dailyAverageKcal = averageDailyConsumptionInFluidOunce * caloricDensityInKcalPerFluidOunce;
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "Hard Cider: " + howOftenTitle(other15HowOften) + " each time " + howMuchTitle(other15HowMuch);

        Category category15 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category15);

        totalDailyAverageFluidOunce = totalDailyAverageFluidOunce + dailyAverageFluidOunce;
        totalDailyAverageKcal = totalDailyAverageKcal + averageDailyCaloricIntakeInKcal;

        /* 
         **********************
         *   Category Total   *
         **********************
         */
        categoryNumber = 16;
        categoryName = "";

        frequencyPerDay = 0.0;
        howOftenTitle = "";

        fluidOuncePerDay = 0.0;
        howMuchTitle = "";

        dailyAverageFluidOunce = totalDailyAverageFluidOunce;   // Total Value
        // Format to show only 3 decimal places
        averageDailyConsumptionInFluidOunce = Double.valueOf(df.format(dailyAverageFluidOunce));

        caloricDensityInKcalPerFluidOunce = 0.0;

        dailyAverageKcal = totalDailyAverageKcal;   // Total Value
        // Format to show only 3 decimal places
        averageDailyCaloricIntakeInKcal = Double.valueOf(df.format(dailyAverageKcal));

        optionsOther = "Total = ";

        Category category16 = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

        items.add(category16);

        /*
        The UserQuestionnaire Table in BevqDB database has the following attributes to set:
            Integer id (The DB Primary Key id value is generated and set at the time of UserQuestionnaire object creation)
            Date    dateEntered
            float   fluidOuncesConsumed
            float   kcalIntake
            String  questionnaire (Stored as MEDIUMTEXT in BevqDB containing the JSON Array of all BEVQ 15 categories)
            User    userId
         */
        //--------------------------------------
        // Create a new UserQuestionnaire object
        //--------------------------------------
        userQuestionnaireController.prepareCreate();

        //-----------------------
        // Set id attribute value 
        //-----------------------
        /*
        The database Primary Key id value is generated and set at the time of UserQuestionnaire object creation.
         */
        //--------------------------------
        // Set dateEntered attribute value 
        //--------------------------------
        LocalDate localDate = LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(localDate);

        // Set Date in the format of YYYY-MM-DD
        userQuestionnaireController.getSelected().setDateEntered(currentDate);

        //----------------------------------------
        // Set fluidOuncesConsumed attribute value 
        //----------------------------------------
        float fluidOunces = averageDailyConsumptionInFluidOunce.floatValue();
        userQuestionnaireController.getSelected().setFluidOuncesConsumed(fluidOunces);

        //-------------------------------
        // Set kcalIntake attribute value 
        //-------------------------------
        float kcal = averageDailyCaloricIntakeInKcal.floatValue();
        userQuestionnaireController.getSelected().setKcalIntake(kcal);

        //----------------------------------
        // Set questionnaire attribute value 
        //----------------------------------
        /*
        ----------------------------------------------------------------------------------------------
        Convert the List of Category objects into an array of JSON objects as depicted below:
        [
            {
                "howOftenTitle":"3+ times per day",
                "howMuchTitle":"8 fl oz (1 cup)",
                "caloricDensityInKcalPerFluidOunce":0,
                "fluidOuncePerDay":8,
                "categoryNumber":1,
                "optionsOther":"Club Soda, Tonic Water: 2-3 times per week each time 8 fl oz (1 cup)",
                "averageDailyCaloricIntakeInKcal":0,
                "frequencyPerDay":3,
                "categoryName":"Water or Unsweetened Sparkling Water",
                "averageDailyConsumptionInFluidOunce":26.856
            },
            :
            :
        ]
        This conversion calls each Getter method in the Category class to define the KEY:VALUE pair of
        a JSON object for each Category object attribute as shown above. If you include other 
        Getter methods in the Category class, their values will also be included in the JSON file.
        Note that the JSON object {...} lists the Category object attributes in no particular order.
        ----------------------------------------------------------------------------------------------
         */
        JSONArray jasonArray = new JSONArray(items);

        // Convert the JSON array into a String
        String questionnaire = jasonArray.toString();

        // Set the questionnaire attribute value
        userQuestionnaireController.getSelected().setQuestionnaire(questionnaire);

        //---------------------------
        // Set userId attribute value 
        //---------------------------
        // Obtain the object reference of the signed-in user
        User signedInUser = (User) Methods.sessionMap().get("user");

        userQuestionnaireController.getSelected().setUserId(signedInUser);

        //----------------------------------------------------------
        // Store the newly created UserQuestionnaire in the database
        //----------------------------------------------------------
        userQuestionnaireController.create();

        //-----------------------------------------------------------
        // Clear the questionnaire content without displaying message
        //-----------------------------------------------------------
        clearQuestionnaire(false);

        return "/index?faces-redirect=true";
    }

    /*
    ***********************************************************
    Return the Title of How Often Given the User Selected Value
    ***********************************************************
     */
    private String howOftenTitle(String itemValue) {

        String howOftenTitle = "";

        switch (itemValue) {
            case "0":
                howOftenTitle = "Never or < 1 time per week";
                break;
            case "0.143":
                howOftenTitle = "1 time per week";
                break;
            case "0.357":
                howOftenTitle = "2-3 times per week";
                break;
            case "0.714":
                howOftenTitle = "4-6 times per week";
                break;
            case "1":
                howOftenTitle = "1 time per day";
                break;
            case "2":
                howOftenTitle = "2 times per day";
                break;
            case "3":
                howOftenTitle = "3+ times per day";
                break;
            default:
                System.out.print("howOftenTitle itemValue is out of range!");
                break;
        }

        return howOftenTitle;
    }

    /*
    **********************************************************
    Return the Title of How Much Given the User Selected Value
    **********************************************************
     */
    private String howMuchTitle(String itemValue) {

        String howMuchTitle = "";

        switch (itemValue) {
            case "0":
                howMuchTitle = "I do not drink it";
                break;
            case "4":
                howMuchTitle = "&lt; 6 fl oz (&frac34; cup)";
                break;
            case "8":
                howMuchTitle = "8 fl oz (1 cup)";
                break;
            case "12":
                howMuchTitle = "12 fl oz (1&frac12; cups)";
                break;
            case "16":
                howMuchTitle = "16 fl oz (2 cups)";
                break;
            case "20":
                howMuchTitle = "20 fl oz (2&frac12; cups)";
                break;
            case "24":
                howMuchTitle = "24 fl oz (3 cups)";
                break;
            case "28":
                howMuchTitle = "28 fl oz (3&frac12; cups)";
                break;
            case "32":
                howMuchTitle = "32 fl oz (4 cups)";
                break;
            default:
                System.out.print("howMuchTitle itemValue is out of range!");
                break;
        }

        return howMuchTitle;
    }

    /*
    ************************************************
    Clear All of the Selections in the Questionnaire
    ************************************************
     */
    public void clearQuestionnaire(Boolean displayMessage) {

        items = null;

        category1HowOften = null;
        category1HowMuch = null;
        category2HowOften = null;
        category2HowMuch = null;
        category3HowOften = null;
        category3HowMuch = null;
        category4HowOften = null;
        category4HowMuch = null;
        category5HowOften = null;
        category5HowMuch = null;

        category6HowOften = null;
        category6HowMuch = null;
        category6option = null;

        category7HowOften = null;
        category7HowMuch = null;
        category8HowOften = null;
        category8HowMuch = null;
        category9HowOften = null;
        category9HowMuch = null;
        category10HowOften = null;
        category10HowMuch = null;

        category11HowOften = null;
        category11HowMuch = null;
        category11option = null;

        category12HowOften = null;
        category12HowMuch = null;
        category12option1 = null;
        category12option2 = null;
        category12option3 = null;

        category13HowOften = null;
        category13HowMuch = null;
        category14HowOften = null;
        category14HowMuch = null;
        category15HowOften = null;
        category15HowMuch = null;

        other1HowOften = null;
        other1HowMuch = null;
        other2HowOften = null;
        other2HowMuch = null;
        other3HowOften = null;
        other3HowMuch = null;
        other6HowOften = null;
        other6HowMuch = null;
        other7HowOften = null;
        other7HowMuch = null;
        other13HowOften = null;
        other13HowMuch = null;
        other15HowOften = null;
        other15HowMuch = null;

        if (displayMessage) {
            Methods.showMessage("Information", "Cleared!", "All Selections are Reset!");
        }
    }

    /*
    *******************************************
    Pre-process the PDF File to be in Landscape 
    Orientation on Letter Size Paper
    *******************************************
     */
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.open();
    }

    /*
    *****************************************
    Warn the User for 5 Minutes of Inactivity
    *****************************************
     */
    public void onIdle() {
        Methods.showMessage("Warning", "No User Action for the Last 5 Minutes!", "Do You Need More Time?");
    }

    /*
    ***************************************************
    Welcome Back the User After 5 Minutes of Inactivity
    ***************************************************
     */
    public void onActive() {
        Methods.showMessage("Warning", "Welcome Back!", "Please Continue Filling Out Your Questionanire!");
    }

}
