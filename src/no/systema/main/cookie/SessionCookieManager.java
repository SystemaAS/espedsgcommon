package no.systema.main.cookie;

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
import no.systema.main.util.AppConstants;

@Data
public class SessionCookieManager {
	private static final Logger logger = Logger.getLogger(SessionCookieManager.class.getName());
	private static int TIME_OUT_VALUE_IN_SECONDS = 1800;
	private final String ENTRY_MODULE_PATH_ESPEDSG2 = "/espedsg2/";
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
	public void addGlobalCookieToken(String cookieValue, HttpServletResponse response){
		
		Cookie cookie = new Cookie(this.tokenId1, cookieValue);
    	cookie.setMaxAge(TIME_OUT_VALUE_IN_SECONDS);
    	cookie.setHttpOnly(true);
    	cookie.setSecure(true);
    	// global cookie accessible every where
    	cookie.setPath("/"); 
    	response.addCookie(cookie);
		
	}
	/**
	 * 
	 * @param cookieValue
	 * @param response
	 */
	public void addLocalCookieToken(String cookieValue, HttpServletResponse response){
		
		Cookie cookie = new Cookie(this.tokenId2, cookieValue);
    	cookie.setMaxAge(TIME_OUT_VALUE_IN_SECONDS);
    	cookie.setHttpOnly(true);
    	cookie.setSecure(true);
    	response.addCookie(cookie);
		
	}
	
	/**
	 * 
	 * @param cookieName
	 * @param request
	 * @return
	 */
	public Map<String,String> getGlobalCookieToken(HttpServletRequest request){
		Map<String,String> retval = new HashMap<String, String>();
		//logger.warn("JSESSIONID:" + request.getSession().getId());
		
		for(Cookie cookie : request.getCookies()){
			//logger.warn(cookie.getName());
			if(cookie.getName().equals(this.tokenId1)){
				retval = this.aesManager.decryptBearer(cookie.getValue());
			}
		}
		
		return retval;
			
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	public Map<String,String> getLocalCookieToken(HttpServletRequest request){
		Map<String,String> retval = new HashMap<String, String>();
		//logger.warn("JSESSIONID:" + request.getSession().getId());
		
		for(Cookie cookie : request.getCookies()){
			//logger.warn(cookie.getName());
			if(cookie.getName().equals(this.tokenId2)){
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
	public void removeGlobalCookie(HttpServletResponse response){
		Cookie cookie = new Cookie(this.tokenId1, null);
    	cookie.setMaxAge(0);
    	cookie.setHttpOnly(true);
    	cookie.setSecure(true);
    	// global cookie accessible every where
    	cookie.setPath("/"); 
    	response.addCookie(cookie);
		
	}
	/**
	 * 
	 * @param response
	 */
	public void removeLocalCookie(HttpServletResponse response){
		Cookie cookie = new Cookie(this.tokenId2, null);
    	cookie.setMaxAge(0);
    	cookie.setHttpOnly(true);
    	cookie.setSecure(true);
    	response.addCookie(cookie);
		
	}
	
	/**
	 * Core check for every request excluding some mappings (refer to ...servlet.xml)
	 * @param cookie
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public boolean isAuthorized (Map<String, String> cookie, HttpServletRequest request){
		boolean retval = false;
		try{
			SystemaWebUser appUser = (SystemaWebUser)request.getSession().getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
			String sessionId = request.getSession().getId();
			//
			if(cookie!=null){
				String cookieSession = (String)cookie.get("session");
		    	String cookieUser = (String)cookie.get("user");
		    	/* DEBUG */
		    	logger.info("cookie:" + cookieSession);
		    	logger.info("cookie:" + cookieUser);
		    	logger.info("session:" + sessionId);
		    	logger.info("session usr:" + appUser.getUser());
		    	
		    	
		    	if(cookieSession!=null && cookieUser!=null){
			    	if(cookieSession.equals(sessionId) && cookieUser.equals(appUser.getUser())){
			    		retval = true;
			    	}
		    	}
			}
		}catch(Exception e){
			logger.fatal(e.toString());
		}
		return retval;
	}
	
	/**
	 * Convenient method to check any user's validity towards the cookie
	 * @param user
	 * @param request
	 * @return
	 */
	public boolean isAuthorized(String user, HttpServletRequest request){
		boolean retval = false;
		
		//default (global cookie)
		Map<String, String> cookie = this.getGlobalCookieToken( request);
		//check for local cookies (if applicable)
		if(!request.getRequestURI().startsWith(ENTRY_MODULE_PATH_ESPEDSG2)){
			//local cookie
			cookie = this.getLocalCookieToken(request);
		}
		if(cookie!=null){
			//check level-1
			if(this.isAuthorized(cookie, request)){
				retval = true;
			}
			//check level-2
			if(user.equals(cookie.get("user"))){
				logger.warn("authorized...");
				retval = true;
			}else{
				logger.fatal("unauthorized...");
				retval = false;
			}
		}
		
		return retval;
	}
	
	/**
	 * 
	 * @param request
	 */
	public void listAllCookies(HttpServletRequest request){

		for(Cookie cookie : request.getCookies()){
			logger.warn(cookie.getName() + ":" + cookie.getValue());
			
		}
		
			
	}
	/**
	 * Clear all
	 * @param request
	 * @param response
	 */
	public void removeAllCookies(HttpServletRequest request, HttpServletResponse response){
		for (Cookie cookie : request.getCookies()) {
		    cookie.setValue("");
		    cookie.setMaxAge(0);
		    cookie.setPath("/");
		    response.addCookie(cookie);
		}
	}
}
