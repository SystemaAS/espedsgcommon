package no.systema.main.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import lombok.Data;
import no.systema.jservices.common.util.AesEncryptionDecryptionManager;
import no.systema.main.model.SystemaWebUser;

@Data
public class SessionCookieManager {
	private static final Logger logger = Logger.getLogger(SessionCookieManager.class.getName());
	private static int TIME_OUT_VALUE_IN_SECONDS = 1800;
	public String tokenId1 = "TUID1";
	public String tokenId2 = "TUID2";
	//
	private AesEncryptionDecryptionManager aesManager = new AesEncryptionDecryptionManager();
	
	
	/**
	 * 
	 * @param cookieName
	 * @param cookieValue
	 * @param response
	 */
	public void addGlobalCookieToken(String cookieName, String cookieValue, HttpServletResponse response){
		
		Cookie cookie = new Cookie(cookieName, cookieValue);
    	cookie.setMaxAge(TIME_OUT_VALUE_IN_SECONDS);
    	cookie.setHttpOnly(true);
    	cookie.setSecure(true);
    	// global cookie accessible every where
    	cookie.setPath("/"); 
    	response.addCookie(cookie);
		
	}
	
	/**
	 * 
	 * @param cookieName
	 * @param request
	 * @return
	 */
	public Map<String,String> getGlobalCookieToken(String cookieName, HttpServletRequest request){
		Map<String,String> retval = new HashMap<String, String>();
		//logger.warn("JSESSIONID:" + request.getSession().getId());
		for(Cookie cookie : request.getCookies()){
			//logger.warn(cookie.getName());
			if(cookie.getName().equals(cookieName)){
				retval = this.aesManager.decryptBearer(cookie.getValue());
			}
		}
		
		return retval;
			
	}
	
	/**
	 * 
	 * @param cookieName
	 * @param cookieValue
	 * @param response
	 */
	public void removeGlobalCookie(String cookieName, HttpServletResponse response){
		Cookie cookie = new Cookie(cookieName, null);
    	cookie.setMaxAge(0);
    	cookie.setHttpOnly(true);
    	cookie.setSecure(true);
    	// global cookie accessible every where
    	cookie.setPath("/"); 
    	response.addCookie(cookie);
		
	}
	
	/**
	 * Core check for every request excluding some mappings (refer to ...servlet.xml)
	 * @param cookie
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public boolean isAuthorized (Map cookie, HttpServletRequest request) throws Exception{
		boolean retval = false;
		
		SystemaWebUser appUser = (SystemaWebUser)request.getSession().getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String sessionId = request.getSession().getId();
		//
		String cookieSession = (String)cookie.get("session");
    	String cookieUser = (String)cookie.get("user");
    	if(cookieSession.equals(sessionId) && cookieUser.equals(appUser.getUser())){
    		retval = true;
    	}
		
		return retval;
	}

}
