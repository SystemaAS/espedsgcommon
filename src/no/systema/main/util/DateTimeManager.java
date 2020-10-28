/**
 * 
 */
package no.systema.main.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import no.systema.jservices.common.util.StringUtils;


/**
 * Utility class to manage date issues
 * 
 * @author oscardelatorre
 * @date Mar 28, 2014
 * 
 */
public class DateTimeManager {
	public static final String ISO_FORMAT = "yyyyMMdd";
	public static final String ISO_FORMAT_REVERSED = "ddMMyyyy";
	public static final String NO_FORMAT = "ddMMyy";
	private static final Logger logger = Logger.getLogger(DateTimeManager.class.getName());
	
	/**
	 * Gets the current ISO date
	 * @return
	 */
	public String getCurrentDate_ISO(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
	
	public String getCurrentDate_ISO(String mask){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(mask);
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
	
	public String getDayNrOfYear(){
		int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		return String.valueOf(dayOfYear);
	}
	public String getYear(){
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return String.valueOf(year);
	}
	public String getDayNrOfYear(String date, String mask){
		DateTimeFormatter f = DateTimeFormatter.ofPattern( mask ) ;
		LocalDate ld = LocalDate.parse( date , f ) ;
		String retval = String.valueOf(ld.getDayOfYear());
		return retval;
	}
	public String getYear(String date, String mask){
		DateTimeFormatter f = DateTimeFormatter.ofPattern( mask ) ;
		LocalDate ld = LocalDate.parse( date , f ) ;
		String retval = String.valueOf(ld.getYear());
		return retval;
	}
	
	public String getCurrentYear(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	public String getCurrentMonth(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		Calendar cal = Calendar.getInstance();
		try{
			
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	/**
	 * The method gets a specific month backwards from the current day (today)
	 * @param numberOfMonths (+)=months forward, (-)=months backwards
	 * @return
	 */
	public String getSpecificMonthFrom_CurrentDate_ISO(int numberOfMonths){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			cal.add(Calendar.MONTH, numberOfMonths);
			retval = dateFormat.format(cal.getTime()); 
			
		}catch(Exception e){
			//Nothing
		}
		
		return  retval; 
	}
	/**
	 * Gets the current NO date
	 * @return
	 */
	public String getCurrentDate_NO(){
		String retval = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(NO_FORMAT);
		Calendar cal = Calendar.getInstance();
		try{
			retval = dateFormat.format(cal.getTime()); 
		}catch(Exception e){
			//Nothing
		}
		return  retval; 
	}
	
	/**
	 * The method compares with current date and compares it with the user value.
	 * A valid backward time should always return a positive value.
	 * @param userValue
	 * @param dateTimeMask
	 * @return
	 */
	public boolean isValidCurrentAndBackwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.equals(today) || userDate.before(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * The method compares with current date and compares it with the user value.
	 * A valid forward time should always return a positive value.
	 * @param userValue
	 * @return
	 * 
	 */
	public boolean isValidCurrentAndForwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.equals(today) || userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	
	public boolean isValidCurrentAndForwardDateNO( String userValue){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(DateTimeManager.NO_FORMAT);
		try{
			if(userValue!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_NO());
					if(userDate.equals(today) || userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	public boolean isValidForwardDateNO( String userValue){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(DateTimeManager.NO_FORMAT);
		logger.warn(userValue);
		logger.warn(this.getCurrentDate_NO());
		
		try{
			if(userValue!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_NO());
					
					if(userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * The method compares with current date and compares it with the user value.
	 * A valid forward time should always return a positive value.
	 * @param userValue
	 * @return
	 * 
	 */
	public boolean isValidForwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * @param userValue
	 * @param dateTimeMask
	 * @return
	 */
	public boolean isValidForwardDateIncludingToday( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		String currentDate = this.getCurrentDate_ISO();
		if(dateTimeMask!=null && dateTimeMask.length()==6){
			currentDate = this.getCurrentDate_NO();
		}
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(currentDate);
					if(userDate.equals(today) || userDate.after(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * 
	 * @param userValue
	 * @param dateTimeMask
	 * @return
	 */
	public boolean isValidBackwardDate( String userValue, String dateTimeMask){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat(dateTimeMask);
		try{
			if(userValue!=null && dateTimeMask!=null){
				//check for the minimum of values in each string
				if(userValue.length()>=4 && dateTimeMask.length()>=2){
					Date userDate = formatter.parse(userValue);
					Date today = formatter.parse(this.getCurrentDate_ISO());
					if(userDate.before(today)){
						retval = true;
					}
				}
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	/**
	 * Compares current time with user time
	 * @param userValue
	 * @param dateMask
	 * @return
	 */
	public boolean isValidForwardTime( String userValue, String dateMask){
		boolean retval = false;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat(dateMask);
			Date userTime = dateFormat.parse(userValue);
			Date currentTime = dateFormat.parse(dateFormat.format(new Date()));

			if (userTime.after(currentTime)){
			    retval = true;
			}
		}catch(Exception e){
			//nothing. the method will return false...
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param userValue
	 * @param dateMask
	 * @return
	 */
	public boolean currentDayBeforeUserDate( String userValue, String dateMask){
		boolean retval = false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date userDate = formatter.parse(userValue);
			Date today = formatter.parse(this.getCurrentDate_ISO());
			if(today.before(userDate)){
				retval = true;
			}
		}catch(Exception e){
			e.toString();
		}
		return retval;
	}
	
	/**
	 * 
	 * @param value
	 * @param dateMask
	 * @return
	 */
	public String getDateFormatted_ISO(String value, String sourceDateMask){
		String newDateString = value;
		final String OLD_FORMAT = sourceDateMask;
		if(value!=null && !"".equals(value)){
			try{
				String oldDateString = value;
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date d = sdf.parse(oldDateString);
				
				sdf.applyPattern(ISO_FORMAT);
				newDateString = sdf.format(d);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return newDateString;
	}
	/**
	 * 
	 * @param value
	 * @param sourceDateMask
	 * @param outputDateMask
	 * @return
	 */
	public String getDateFormatted_ISO(String value, String sourceDateMask, String outputDateMask){
		String newDateString = value;
		final String OLD_FORMAT = sourceDateMask;
		if(value!=null && !"".equals(value)){
			try{
				String oldDateString = value;
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date d = sdf.parse(oldDateString);
				
				sdf.applyPattern(outputDateMask);
				newDateString = sdf.format(d);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return newDateString;
	}
	
	/**
	 * 
	 * @param value
	 * @param dateMask
	 * @return
	 */
	public String getDateFormatted_NO(String value, String sourceDateMask){
		String newDateString = value;
		final String OLD_FORMAT = sourceDateMask;
		
		if(value!=null && !"".equals(value) && !value.equals("0")){
			try{
				String oldDateString = value;
	
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date d = sdf.parse(oldDateString);
				sdf.applyPattern(NO_FORMAT);
				newDateString = sdf.format(d);
				//System.out.println(newDateString);
							
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return newDateString;
	}
	
	/**
	 * User date: 29 = yyyyMM29 (current year and current month) ISO
	 * User date: 2910 = yyy1029 (current year + user month + user day) ISO
	 * 
	 * @param userDate
	 * @return
	 */
	public String adjustUserDateToISODate(String userDate){
		String retval = userDate;
		if(userDate!=null && !"".equals(userDate) && userDate.length()<8){
			if(userDate.length()==2){
				retval = this.getCurrentYear() + this.getCurrentMonth() + userDate;
			}else if (userDate.length()==4){
				String day = userDate.substring(0,2);String month = userDate.substring(2);
				retval = this.getCurrentYear() + month + day;
			}else{
				//nothing
			}
		}
		return retval;
	}

	/**
	 * User time: 23 = 2300
	 * 
	 * @param userTime
	 * @return
	 */
	public String adjustUserTimeToHHmm(String userTime){
		String MINUTES_SUFFIX = "00";
		String HOUR_PREFIX = "0";
		String retval = userTime;
		if(userTime!=null && !"".equals(userTime) && userTime.length()<4){
			if(userTime.length()==3){
				retval = HOUR_PREFIX + userTime;
			}else if(userTime.length()==2){
				retval = userTime + MINUTES_SUFFIX;
			}else if(userTime.length()==1){
				retval = HOUR_PREFIX + userTime + MINUTES_SUFFIX;
			}else{
				//nothing
			}
		}
		return retval;
	}
	
	/**
	 * 
	 * @param lowerLimitDate
	 * @param lowerLimitDateMask
	 * @param upperLimitDate
	 * @param upperLimitDateMask
	 * @return
	 */
	public boolean validTodayBetweenLimits( String lowerLimitDate, String lowerLimitDateMask, String upperLimitDate, String upperLimitDateMask){
		boolean retval = false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date today = formatter.parse(this.getCurrentDate_ISO());
			//lower limit date
			SimpleDateFormat formatterLowerLimit = new SimpleDateFormat(lowerLimitDateMask);
			Date lowerDate = formatterLowerLimit.parse(lowerLimitDate);
			
			if(upperLimitDate!=null && !"".equals(upperLimitDate) && !"0".equals(upperLimitDate)){
				SimpleDateFormat formatterUpperLimit = new SimpleDateFormat(upperLimitDateMask);
				Date upperDate = formatterUpperLimit.parse(upperLimitDate);
				if( (today.after(lowerDate) || today.compareTo(lowerDate)==0) && (today.before(upperDate) || today.compareTo(upperDate)==0)){
					retval = true;
				}
			}else{
				//only consider lower limit date
				if(today.after(lowerDate) || today.compareTo(lowerDate)==0){
					retval = true;
				}
			}
			
		}catch(Exception e){
			e.toString();
		}
		return retval;
	}
	
	/**
	 * 
	 * @param lowerLimitDate
	 * @param lowerLimitDateMask
	 * @return
	 */
	public boolean validTodayBetweenLimits( String lowerLimitDate, String lowerLimitDateMask){
		boolean retval = false;
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date today = formatter.parse(this.getCurrentDate_ISO());
			//lower limit date
			SimpleDateFormat formatterLowerLimit = new SimpleDateFormat(lowerLimitDateMask);
			Date lowerDate = formatterLowerLimit.parse(lowerLimitDate);
			
			//only consider lower limit date
			if(today.after(lowerDate) || today.compareTo(lowerDate)==0){
				retval = true;
			}
			
			
			
		}catch(Exception e){
			e.toString();
		}
		return retval;
	}
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public boolean validTwoDatesWithinSpanISO(String date1, String date2, int limitToCompare){
		boolean retval = true;
		if(StringUtils.hasValue(date1) && StringUtils.hasValue(date2) ){
			try{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			    Date firstDate = formatter.parse(date1);
			    Date secondDate = formatter.parse(date2);
					 
			    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			    Long tmp = new Long(diff);
			    int x = tmp.intValue();
			    //System.out.println("x:" + x);
			    //System.out.println("limit:" + limitToCompare);
			    if(x>=limitToCompare){
			    	retval = false;
			    }
			    
			}catch(Exception e){
				e.toString();
			}
		}
	    return retval;

	}
	/**
	 * Send -10 or 10 if you want to get a new date after a date operation 
	 * @param days
	 * @return
	 */
	public String getNewDateFromNow( int days){
		String retval = "";
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add( Calendar.DAY_OF_YEAR, days);
		Date daysAgoOrAhead = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		retval = formatter.format(daysAgoOrAhead);
		
		return retval;
	}
	/**
	 * 
	 * @param formatMask
	 * @param days
	 * @return
	 */
	public String getNewDateFromNow( String formatMask, int days){
		String retval = "";
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add( Calendar.DAY_OF_YEAR, days);
		Date daysAgoOrAhead = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(formatMask);
		retval = formatter.format(daysAgoOrAhead);
		
		return retval;
	}
	
	/**
	 * 
	 * @param date
	 * @param dateMask
	 * @return
	 */
	public Long getRangeOfDaysBetweenDates(String date, String dateMask){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateMask);
	    LocalDate startDate = LocalDate.parse(date, formatter);
	    LocalDate endtDate = LocalDate.now();
	    // Range = End date - Start date
	    return ChronoUnit.DAYS.between(startDate, endtDate);
	    //urlRequestParams.append("&dftdg=" + String.valueOf(range));
    
	}
	/**
	 * 
	 * @param userValue
	 * @param mask
	 * @return
	 */
	public boolean isToday(String userValue, String mask){
		boolean retval = false;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat(mask);
			String x = dateFormat.format(Calendar.getInstance().getTime());
			Date now = dateFormat.parse(x);
			Date userTime = dateFormat.parse(userValue);
			if(now.compareTo(userTime)==0){
				retval = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	    return retval;
	}
	
	/**
	 * 
	 * @param timeUserValue
	 * @param hoursFromNow time to consider from NOW. 2, 3, etc hours ahead
	 * @return
	 */
	public boolean isValidTime(String timeUserValue, int hoursFromNow){
		boolean retval = false;
		try{
			Calendar now = Calendar.getInstance();
		    // Incrementing hours by _LIMIT_HOURS
		    Calendar calendar2 = Calendar.getInstance();
		    calendar2.add(Calendar.HOUR_OF_DAY, + hoursFromNow);
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
			Date userTime = dateFormat.parse(timeUserValue);
			String minimumHour = String.valueOf(calendar2.get(Calendar.HOUR_OF_DAY));
			String minimumMinute = String.valueOf(calendar2.get(Calendar.MINUTE));
			Date minimumTime = dateFormat.parse(minimumHour + minimumMinute);
	
			if (userTime.after(minimumTime)){
			    //logger.warn("Ahead!");
			    retval = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return retval;
		
	}
}
