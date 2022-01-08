package no.systema.main.validator;

import org.slf4j.*;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.model.jsonjackson.general.notisblock.JsonNotisblockRecord;
import no.systema.main.util.StringManager;

/**
 * 
 * @author oscardelatorre
 * @date Jan 20, 2015
 * 
 *
 */
public class NotisblockValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(NotisblockValidator.class.getName());
	private DateValidator dateValidator = new DateValidator();
	private StringManager strMgr = new StringManager();
	/**
	* This Validator validates just User instances 
	* 
	**/
	public boolean supports(Class clazz) {
		return JsonNotisblockRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 

		JsonNotisblockRecord record = (JsonNotisblockRecord) obj;
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frtdt", "notisblock.header.item.null.date_frtdt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frtkod", "Part", "Part er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "frttxt", "Text", "Tekst er obligtorisk");
		
		
		//Check rules
		if(record!=null){
			//------
			//dates 
			//------
			if(strMgr.isNotNull(record.getFrtdt())){
				if(!dateValidator.validateDate(record.getFrtdt(), DateValidator.DATE_MASK_ISO)){
					errors.rejectValue("frtdt", "Dato er ugyldig", "Dato er ugyldig"); 	
				}
			}
		}
	}
	
	
}
