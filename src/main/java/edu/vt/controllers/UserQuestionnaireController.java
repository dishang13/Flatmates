/*
 * Created by Osman Balci on 2021.2.27
 * Copyright © 2021 Osman Balci. All rights reserved.
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.UserFacade;
import edu.vt.EntityBeans.UserQuestionnaire;
import edu.vt.FacadeBeans.UserQuestionnaireFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.globals.Methods;
import edu.vt.pojo.Category;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

@Named("userQuestionnaireController")
@SessionScoped

public class UserQuestionnaireController implements Serializable {

    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
    @EJB
    private UserFacade userFacade;

    @EJB
    private UserQuestionnaireFacade userQuestionnaireFacade;

    // 'items' is a List containing the object references of UserQuestionnaire objects
    private List<UserQuestionnaire> items = null;

    // 'selected' contains the object reference of the selected UserQuestionnaire object
    private UserQuestionnaire selected;

    // 'bevqItems' is a List containing the object references of Category objects
    private List<Category> bevqItems = null;

    private String answerToSecurityQuestion;

    /*
    ==================
    Constructor Method
    ==================
     */
    public UserQuestionnaireController() {
    }

    /*
    =========================
    Getter and Setter Methods
    =========================
     */
    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    private UserQuestionnaireFacade getUserQuestionnaireFacade() {
        return userQuestionnaireFacade;
    }

    public void setUserQuestionnaireFacade(UserQuestionnaireFacade userQuestionnaireFacade) {
        this.userQuestionnaireFacade = userQuestionnaireFacade;
    }

    /*
    ***************************************************************
    Return the List of Questionnaires Created by the Signed-In User
    ***************************************************************
     */
    public List<UserQuestionnaire> getItems() {
        if (items == null) {
            /*
            user_id (database primary key) was put into the SessionMap in the
            initializeSessionMap() method in LoginManager upon user's sign in.
             */
            int userPrimaryKey = (int) Methods.sessionMap().get("user_id");

            items = getUserQuestionnaireFacade().findUserQuestionnairesByUserPrimaryKey(userPrimaryKey);
        }
        return items;
    }

    public void setItems(List<UserQuestionnaire> items) {
        this.items = items;
    }

    public UserQuestionnaire getSelected() {
        return selected;
    }

    public void setSelected(UserQuestionnaire selected) {
        bevqItems = null;    // Invalidate list of BEVQ items to trigger re-query.
        this.selected = selected;
    }

    public List<Category> getBevqItems() {
        if (bevqItems == null) {

            bevqItems = new ArrayList<>();

            String questionnaire = selected.getQuestionnaire();
            JSONArray jsonArray = new JSONArray(questionnaire);

            /*
            ======================================================================
            The jsonArray obtained from the 'questionnaire' contains JSON objects.
            Each JSON object has the following KEY-VALUE pairings:
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
            ======================================================================
             */
            jsonArray.forEach(object -> {
                // Typecast the object as JSONObject
                JSONObject jsonObject = (JSONObject) object;

                String howOftenTitle = jsonObject.getString("howOftenTitle");
                String howMuchTitle = jsonObject.getString("howMuchTitle");
                Double caloricDensityInKcalPerFluidOunce = jsonObject.getDouble("caloricDensityInKcalPerFluidOunce");
                Double fluidOuncePerDay = jsonObject.getDouble("fluidOuncePerDay");
                Integer categoryNumber = jsonObject.getInt("categoryNumber");
                String optionsOther = jsonObject.getString("optionsOther");
                Double averageDailyCaloricIntakeInKcal = jsonObject.getDouble("averageDailyCaloricIntakeInKcal");
                Double frequencyPerDay = jsonObject.getDouble("frequencyPerDay");
                String categoryName = jsonObject.getString("categoryName");
                Double averageDailyConsumptionInFluidOunce = jsonObject.getDouble("averageDailyConsumptionInFluidOunce");

                // Create a Category object using the attributes (Key-Value pairs) of the jsonObject
                Category category = new Category(categoryNumber, categoryName, howOftenTitle, frequencyPerDay, howMuchTitle, fluidOuncePerDay,
                        optionsOther, averageDailyConsumptionInFluidOunce, caloricDensityInKcalPerFluidOunce, averageDailyCaloricIntakeInKcal);

                // Add newly created Category object to the ArrayList
                bevqItems.add(category);
            });
        }
        return bevqItems;
    }

    public void setBevqItems(List<Category> bevqItems) {
        this.bevqItems = bevqItems;
    }

    public String getAnswerToSecurityQuestion() {
        return answerToSecurityQuestion;
    }

    public void setAnswerToSecurityQuestion(String answerToSecurityQuestion) {
        this.answerToSecurityQuestion = answerToSecurityQuestion;
    }

    /*
    ================
    Instance Methods
    ================

    *****************************************************
    Process the Submitted Answer to the Security Question
    *****************************************************
     */
    public void securityAnswerSubmit() {
        /*
        'user', the object reference of the signed-in user, was put into the SessionMap
        in the initializeSessionMap() method in LoginManager upon user's sign in.
         */
        User signedInUser = (User) Methods.sessionMap().get("user");

        String actualSecurityAnswer = signedInUser.getSecurityAnswer();
        String enteredSecurityAnswer = getAnswerToSecurityQuestion();

        if (actualSecurityAnswer.equals(enteredSecurityAnswer)) {
            // Answer to the security question is correct. Delete the selected questionnaire.
            deleteQuestionnaire();

        } else {
            Methods.showMessage("Error", "Answer to the Security Question is Incorrect!", "");
        }
    }

    /*
    *************************************
    Prepare to Create a New Questionnaire
    *************************************
     */
    public UserQuestionnaire prepareCreate() {
        /*
        Instantiate a new UserQuestionnaire object and store its object reference into instance
        variable 'selected'. The UserQuestionnaire class is defined in UserQuestionnaire.java
         */
        selected = new UserQuestionnaire();

        // Return the object reference of the newly created UserQuestionnaire object
        return selected;
    }

    /*
    ******************************************
    Create a New Questionnaire in the Database
    ******************************************
     */
    public void create() {
        /*
        We need to preserve the messages since we will redirect to show a
        different JSF page after successful creation of the questionnaire.
         */
        Methods.preserveMessages();
        /*
        Show the message "Thank You! Your Questionnaire was Successfully Saved in the Database!"
        given in the Bundle.properties file under the UserQuestionnaireCreated keyword.

        Prevent displaying the same msg twice, one for Summary and one for Detail, by setting the 
        message Detail to "" in the addSuccessMessage(String msg) method in the jsfUtil.java file.
         */
        persist(PersistAction.CREATE,"Thank You! Your Questionnaire was Successfully Saved in the Database!");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The CREATE operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    // We do not allow update of a questionnaire.
    public void update() {
        persist(PersistAction.UPDATE,"User Questionnaire was successfully updated.");
    }

    /*
    ***************************************************
    Delete the Selected Questionnaire from the Database
    ***************************************************
     */
    public void deleteQuestionnaire() {
        /*
        We need to preserve the messages since we will redirect to show a
        different JSF page after successful deletion of the questionnaire.
         */
        Methods.preserveMessages();
        /*
        Show the message "The Questionnaire was Successfully Deleted from the Database!"
        given in the Bundle.properties file under the UserQuestionnaireDeleted keyword.
        
        Prevent displaying the same msg twice, one for Summary and one for Detail, by setting the 
        message Detail to "" in the addSuccessMessage(String msg) method in the jsfUtil.java file.
         */
        persist(PersistAction.DELETE,"The Questionnaire was Successfully Deleted from the Database!\n");
        if (!JsfUtil.isValidationFailed()) {
            // No JSF validation error. The DELETE operation is successfully performed.
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    /*
     ****************************************************************************
     *   Perform CREATE, EDIT (UPDATE), and DELETE Operations in the Database   *
     ****************************************************************************
     */
    /**
     * @param persistAction refers to CREATE, UPDATE (Edit) or DELETE action
     * @param successMessage displayed to inform the user about the result
     */
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            try {
                if (persistAction != PersistAction.DELETE) {
                    /*
                     -------------------------------------------------
                     Perform CREATE or EDIT operation in the database.
                     -------------------------------------------------
                     The edit(selected) method performs the SAVE (STORE) operation of the "selected"
                     object in the database regardless of whether the object is a newly
                     created object (CREATE) or an edited (updated) object (EDIT or UPDATE).
                    
                     UserQuestionnaireFacade inherits the edit(selected) method from the AbstractFacade class.
                     */
                    getUserQuestionnaireFacade().edit(selected);
                } else {
                    /*
                     -----------------------------------------
                     Perform DELETE operation in the database.
                     -----------------------------------------
                     The remove method performs the DELETE operation in the database.
                    
                     UserQuestionnaireFacade inherits the remove(selected) method from the AbstractFacade class.
                     */
                    getUserQuestionnaireFacade().remove(selected);
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
                    JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex,"A persistence error occurred.");
            }
        }
    }

    /*
    ************************************************
    |   Other Auto Generated Methods by NetBeans   |
    ************************************************
     */
    public UserQuestionnaire getUserQuestionnaire(Integer id) {
        return getUserQuestionnaireFacade().find(id);
    }

    public List<UserQuestionnaire> getItemsAvailableSelectMany() {
        return getUserQuestionnaireFacade().findAll();
    }

    public List<UserQuestionnaire> getItemsAvailableSelectOne() {
        return getUserQuestionnaireFacade().findAll();
    }

    @FacesConverter(forClass = UserQuestionnaire.class)
    public static class UserQuestionnaireControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserQuestionnaireController controller = (UserQuestionnaireController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userQuestionnaireController");
            return controller.getUserQuestionnaire(getKey(value));
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
            if (object instanceof UserQuestionnaire) {
                UserQuestionnaire o = (UserQuestionnaire) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}",
                        new Object[]{object, object.getClass().getName(), UserQuestionnaire.class.getName()});
                return null;
            }
        }

    }

    /*
    ***************************************************
    Type Converter Methods for PrimeFaces Data Exporter 
    Function to Export Data into PDF, Excel, and CSV
    ***************************************************
    
    PrimeFaces p:dataExporter requires the exported values to be of String type.
    These methods are called in QuestionnaireDetails.xhtml.
     */
    public String convertIntToString(Integer value) {
        return Integer.toString(value);
    }

    public String convertDoubleToString(Double value) {
        return Double.toString(value);
    }
}
