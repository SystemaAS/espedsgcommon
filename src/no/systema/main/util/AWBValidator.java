/**
 * 
 */
package no.systema.main.util;

import no.systema.main.util.StringManager;
/**
 * 
 * @author oscardelatorre
 * @date Jan 2019
 * 
 * Method of calculating the AWB-check digit
 *
 * The MAWB number consists of a total 11 digits.
 * These 11 digits can be divided into 3 parts
 * [1] The first 3 digits are the Airline Prefix which are unique for every airline
 * [2] The next 7 digits is the Serial Number of the AWB
 * [3] The last digit is the Check digit
 *
 * For example the AWB number is 589 8114074 3 (Check digit = 3)
 * Mod7 is used.
 * 
 */
public class AWBValidator {
	StringManager strMgr = new StringManager();
	
	/**
	 * Checks t
	 * @param value
	 * @return
	 */
	public boolean validateMod7 (String value){
		boolean retval = false;
		
		if(strMgr.isNotNull(value)){
			//check length (the airline prefix can be between 1-999 therefore the triple check in length
			if(value.length()==9 || value.length()==10 || value.length()==11){
				//(1) Now separate the diferente codes: 3-first chars for the airline code and check-digit (last char)
				String awbSerialNoRaw = value.substring(value.length()-8);
				String checkDigitStr = awbSerialNoRaw.substring(7);
				//(2) convert to numeric
				String awbSerialNoStr = awbSerialNoRaw.substring(0, 7);
				Integer checkDigit = Integer.valueOf(checkDigitStr);
				Integer awbSerialNo = Integer.valueOf(awbSerialNoStr);
				//(3) math MOD7
				Integer result = awbSerialNo % 7;
				if(result == checkDigit){
					retval = true;
				}
			}
				
		}
		return retval;
	}
	
	
	
	
}
