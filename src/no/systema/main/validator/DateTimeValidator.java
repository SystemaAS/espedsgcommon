package no.systema.main.validator;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.LocalDateTime;


import org.apache.log4j.Logger;
import no.systema.main.util.StringManager;

/**
 * Utility class
 * @author Frederich Müller Dreisig
 * 
 * 
 */
public class DateTimeValidator {
	public static final String DATE_MASK_NO = "ddMMuu";
	public static final String DATE_MASK_ISO = "uuuuMMdd";
	
	private static final Logger logger = Logger.getLogger(DateTimeValidator.class.getName());
	private StringManager strMgr = new StringManager();
	/**
	 * 
	 * @param value
	 * @param dateFormat
	 * @return
	 */
	public boolean validateDate(String value, String dateFormatMask){
		boolean retval = false;
		
		if(strMgr.isNotNull(value)){
			try{
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatMask).withResolverStyle(ResolverStyle.STRICT);
				dateTimeFormatter.parse(value);
				//LocalDateTime validDate = LocalDateTime.parse(value, dateTimeFormatter);
				retval = true;
				
			}catch(Exception e){
				e.toString();
			}
		}
		
		return retval;
	}
	
	



	
}
