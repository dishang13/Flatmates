/*
 * Created by Dishang, Siva on 2021.04.10
 * Copyright © 2021 Dishang, Siva. All rights reserved.
 */
package edu.vt.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
The @FacesValidator annotation on a class automatically registers the class with the runtime as a Validator.
The "usernameValidator" attribute is the validator-id used in the JSF facelets page CreateAccount.xhtml within

    <f:validator validatorId="usernameValidator" />

to invoke the "validate" method of the annotated class given below.
*/
@FacesValidator("dateValidator")

public class DateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Get UIInput fields
        UIInput startDateInput = (UIInput) component.getAttributes().get("hiddenStartDate");
        UIInput endDateInput = (UIInput) component.getAttributes().get("hiddenEndDate");

        // Null validation is handled by required="true"
        if(startDateInput == null || endDateInput == null) {
            return;
        }

        // Get UIInput field values
        String startDateInputString = (String) startDateInput.getSubmittedValue();
        String endDateInputString = (String) endDateInput.getSubmittedValue();

        // Null validation is handled by required="true"
        if(startDateInputString.isBlank() || endDateInputString.isBlank()) {
            return;
        }

        // Create a date formatter
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

        try {
            // Convert string to date
            Date startDate = formatter.parse(startDateInputString);
            Date endDate = formatter.parse(endDateInputString);

            // Validate dates
            if(startDate.after(endDate)) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Save Failed!", "The Start Date must be before End Date")) ;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
