package no.systema.main.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

public class IPAddressValidator {
	
	private Pattern pattern;
    private Matcher matcher;
 
    private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	  
    public IPAddressValidator(){
	  pattern = Pattern.compile(IPADDRESS_PATTERN);
    }
    
    /**
     * Validate ip address with regular expression
     * @param ip ip address for validation
     * @return true valid ip address, false invalid ip address
     */
     public boolean validate(final String ip){		  
 	  matcher = pattern.matcher(ip);
 	  return matcher.matches();	    	    
     }
     
     /**
 	 * There should be no ip-addresses ...
 	 * WTGs-Penetration Test states that only white-list addresses but we should block all FilePaths-outside SYSPED.
 	 * This UCase should not bring any absoluteFilePath outside a mounted /pdf/... within the tomcat range AS400
 	 * 
 	 * @param absoluteFilePath
 	 * @return false if there is an indication of a redirection to other ip-address
 	 * 
 	 */
 	public boolean isValidAbsoluteFilePathFor_RenderFile(String absoluteFilePath){
 		String [] INVALID_PREFIXES = {"http","file","ftp","news","mailto","telnet","gopher","wais"};
 		
 		boolean retval = true;
 		for (int i = 0; i < INVALID_PREFIXES.length; i++) { 
 		    if(absoluteFilePath!=null && absoluteFilePath.startsWith(INVALID_PREFIXES[i])){
 		    	retval = false;
 		    	break;
            }else if (absoluteFilePath!=null && absoluteFilePath.contains(INVALID_PREFIXES[i])){
            	retval = false;
            	break;
            }
        } 
 		
 		return retval;
 	}
}
