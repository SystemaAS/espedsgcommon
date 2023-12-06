package no.systema.main.validator;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;
import java.time.format.DateTimeFormatter;
import org.slf4j.*;

import no.systema.main.util.DateTimeManager;
import no.systema.main.util.StringManager;

/**
 * Utility class
 * @author oscardelatorre
 * 
 * 
 */
public class DateValidator {
	public static final String DATE_MASK_NO = "ddMMyy";
	public static final String DATE_MASK_ISO = "yyyyMMdd";
	
	private static final Logger logger = LoggerFactory.getLogger(DateValidator.class.getName());
	//This pattern checks ONLY the logical part of HH:mm
	//The logical part for a date, including leap years is too complicated to implement here. 
	//Java and Apache mechanisms do not include any nice library to avoid this issue, therefore we do not implement any logical control on date, ONLY in time.
	private final String DATE_ISO_203_YYYYMMDDHHmm = "^\\d{4}\\d{2}\\d{2}(([0-1]?[0-9])|(2[0-3]))[0-5][0-9]$";
	//private final String DATE_ISO_YYYYMMDD = "^\\d{4}\\d{2}\\d{2}$";
	private final String DATE_ISO_YYYYMMDD = "^(19|20|99)\\d\\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$";
	//private final String DATE_ISO_YYYY_MM_DD = "^\\d{4}-\\d{2}-\\d{2}$"; // Light alternative
	private final String DATE_ISO_YYYY_MM_DD = "^(19|20|99)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
	//HHmm only
	private final String TIME_HHmm = "(([0-1]?[0-9])|(2[0-3]))[0-5][0-9]"; //OK HHmm
	//HHmm only
	private final String TIME_HHmmss = "(([0-1]?[0-9])|(2[0-3]))[0-5][0-9][0-5][0-9]"; //OK HHmmss
	
	///*
	/**
	 * RegExp to test a string for a ISO 8601 Date spec
	 *  YYYY
	 *  YYYY-MM
	 *  YYYY-MM-DD
	 *  YYYY-MM-DDThh:mmTZD
	 *  YYYY-MM-DDThh:mm:ssTZD
	 *  YYYY-MM-DDThh:mm:ss.sTZD
	 * @see: https://www.w3.org/TR/NOTE-datetime
	 * @type {RegExp}
	 
	private final String ISO_8601 = /^\d{4}(-\d\d(-\d\d(T\d\d:\d\d(:\d\d)?(\.\d+)?(([+-]\d\d:\d\d)|Z)?)?)?)?$/i
	 */

	
	StringManager strMgr = new StringManager();
	
    /**
     * validates date as in correct mask
     * @param str
     * 
     */
    public boolean validateDateIso203_YYYYMMDDhhmm(String str){
    		boolean retval = false;
    		Pattern pattern = Pattern.compile(this.DATE_ISO_203_YYYYMMDDHHmm);
        //check date pattern
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()){
        		//Now check if it is logically correct
    			retval = true;
            //System.out.println("MATCH on dtm!");
        }else{
        		//System.out.println("ERROR!");
        }
        return retval;
    }
    /**
     * 
     * @param str
     * @return
     */
    public boolean validateDateIso203_YYYYMMDD(String str){
		boolean retval = false;
		Pattern pattern = Pattern.compile(this.DATE_ISO_YYYYMMDD);
		//check date pattern
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()){
	    		//Now check if it is logically correct
			retval = true;
	        //System.out.println("MATCH on dtm!");
		}else{
    			//System.out.println("ERROR!");
		}
		return retval;
    }
    /**
     * Whit HYPHENs YYYY-MM-dd
     * @param str
     * @return
     */
    public boolean validateDateIso203_YYYY_MM_DD(String str){
		boolean retval = false;
		Pattern pattern = Pattern.compile(this.DATE_ISO_YYYY_MM_DD);
		//check date pattern
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()){
	    		//Now check if it is logically correct
			retval = true;
	        //System.out.println("MATCH on dtm!");
		}else{
    			//System.out.println("ERROR!");
		}
		return retval;
    }

    
    /**
     * 
     * @param str
     * @return
     */
    public boolean validateTimeHHmm(String str){
		boolean retval = false;
		if(str!=null && str.length()==2){
			retval = true;
		}else{
			Pattern pattern = Pattern.compile(this.TIME_HHmm);
			//check date pattern
			Matcher matcher = pattern.matcher(str);
			if (matcher.matches()){
	    			//Now check if it is logically correct
				retval = true;
				//System.out.println("MATCH on dtm!");
			}else{
				//System.out.println("ERROR!");
			}
		}
		return retval;
    }
    
    public boolean validateTimeHHmmss(String str){
		boolean retval = false;
		if(str!=null && str.length()==2){
			retval = true;
		}else{
			Pattern pattern = Pattern.compile(this.TIME_HHmmss);
			//check date pattern
			Matcher matcher = pattern.matcher(str);
			if (matcher.matches()){
	    			//Now check if it is logically correct
				retval = true;
				//System.out.println("MATCH on dtm!");
			}else{
				//System.out.println("ERROR!");
			}
		}
		return retval;
    }
    
    /**
     * 
     * @param str
     * @param mask
     * @return
     */
    public boolean validateDate(String str, String mask){
		boolean retval = false;
		
		if(strMgr.isNotNull(str)){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(mask);
				sdf.setLenient(false);
				Date date = sdf.parse(str);
				//return
				retval = true;
				
			}catch(Exception e){
				e.toString();
		
			}
		}
		return retval;
    }
    /**
     * 
     * @param value
     * @return
     */
    public boolean validateTime24Hours(String value){
		boolean retval = false;
		if(strMgr.isNotNull(value)){
			//String TIME24HOURS_PATTERN =  "([01]?[0-9]|2[0-3]):[0-5][0-9]"; //with ":" separator
			String TIME24HOURS_PATTERN =  "([01]?[0-9]|2[0-3])[0-5][0-9]";
			retval = Pattern.matches(TIME24HOURS_PATTERN, value);
		}	
		return retval;
    }
    
    /**
    * 
    * @param value
    * @return
    */
    public boolean validateTime12Hours(String value){
    	boolean retval = false;
		if(strMgr.isNotNull(value)){
			String TIME12HOURS_PATTERN =  "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)";
			retval = Pattern.matches(TIME12HOURS_PATTERN, value);
		}	
		return retval;
    }
    /**
     * Elucidates the validity of a driver (older than 18 years)
     * @return
     * @throws Exception
     */
    public boolean validDrivingAgeNorway(String dateNO) {
    	boolean retval = true;
	    SimpleDateFormat formater=new SimpleDateFormat(DATE_MASK_NO);
		DateTimeManager dtMgr = new DateTimeManager();
		
		String now = dtMgr.getCurrentDate_NO();
		logger.info(now);
		try{
			long d1=formater.parse(now).getTime();
			long d2=formater.parse(dateNO).getTime();
			
			long result= Math.abs((d1-d2)/(1000*60*60*24))/365;
			if(result<18){
				retval = false;
			}
			//logger.info(Math.abs((d1-d2)/(1000*60*60*24))/365);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retval;
    }
    
}
