package gestionnaire.controllers;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("myconstraints.Mail")
public class MailValidator implements Validator {

	private Pattern pattern;
	  
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		if(value == null) {
            return;
        }
		pattern = Pattern.compile(EMAIL_PATTERN);
        if(!pattern.matcher(value.toString()).matches()) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", 
                        value + " is not a valid email;"));
        }
		
	}

}
