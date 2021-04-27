/*
 * Created by Osman Balci on 2021.2.27
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.pojo;

// This class provides a Plain Old Java Object (POJO) representing a BEVQ category
public class Category {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
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

    /*
    ==================
    Constructor Method
    ==================
     */
    public Category(Integer categoryNumber, String categoryName, String howOftenTitle, Double frequencyPerDay, String howMuchTitle, Double fluidOuncePerDay, String optionsOther, Double averageDailyConsumptionInFluidOunce, Double caloricDensityInKcalPerFluidOunce, Double averageDailyCaloricIntakeInKcal) {
        this.categoryNumber = categoryNumber;
        this.categoryName = categoryName;
        this.howOftenTitle = howOftenTitle;
        this.frequencyPerDay = frequencyPerDay;
        this.howMuchTitle = howMuchTitle;
        this.fluidOuncePerDay = fluidOuncePerDay;
        this.optionsOther = optionsOther;
        this.averageDailyConsumptionInFluidOunce = averageDailyConsumptionInFluidOunce;
        this.caloricDensityInKcalPerFluidOunce = caloricDensityInKcalPerFluidOunce;
        this.averageDailyCaloricIntakeInKcal = averageDailyCaloricIntakeInKcal;
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public Integer getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(Integer categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getHowOftenTitle() {
        return howOftenTitle;
    }

    public void setHowOftenTitle(String howOftenTitle) {
        this.howOftenTitle = howOftenTitle;
    }

    public Double getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(Double frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    public String getHowMuchTitle() {
        return howMuchTitle;
    }

    public void setHowMuchTitle(String howMuchTitle) {
        this.howMuchTitle = howMuchTitle;
    }

    public Double getFluidOuncePerDay() {
        return fluidOuncePerDay;
    }

    public void setFluidOuncePerDay(Double fluidOuncePerDay) {
        this.fluidOuncePerDay = fluidOuncePerDay;
    }

    public String getOptionsOther() {
        return optionsOther;
    }

    public void setOptionsOther(String optionsOther) {
        this.optionsOther = optionsOther;
    }

    public Double getAverageDailyConsumptionInFluidOunce() {
        return averageDailyConsumptionInFluidOunce;
    }

    public void setAverageDailyConsumptionInFluidOunce(Double averageDailyConsumptionInFluidOunce) {
        this.averageDailyConsumptionInFluidOunce = averageDailyConsumptionInFluidOunce;
    }

    public Double getCaloricDensityInKcalPerFluidOunce() {
        return caloricDensityInKcalPerFluidOunce;
    }

    public void setCaloricDensityInKcalPerFluidOunce(Double caloricDensityInKcalPerFluidOunce) {
        this.caloricDensityInKcalPerFluidOunce = caloricDensityInKcalPerFluidOunce;
    }

    public Double getAverageDailyCaloricIntakeInKcal() {
        return averageDailyCaloricIntakeInKcal;
    }

    public void setAverageDailyCaloricIntakeInKcal(Double averageDailyCaloricIntakeInKcal) {
        this.averageDailyCaloricIntakeInKcal = averageDailyCaloricIntakeInKcal;
    }

}
