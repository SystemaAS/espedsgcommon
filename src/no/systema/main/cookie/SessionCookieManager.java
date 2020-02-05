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
	private static int TIME_OUT_VALUE_IN_SECONDS = 3600;
	private final String ENTRY_MODULE_PATH_ESPEDSG2 = "/espedsg2/";
	
	private String tokenId1 = "TUID1";
	private String tokenId2 = "TUID2";
	private String tokenId2Suffix = "";
	private boolean httpsProtocol = false;
	//
	private AesEncryptionDecryptionManager aesManager = new AesEncryptionDecryptionManager();
	
	//this could be used if the strategy for every single local module is to have a unique TUID2_<x-name>. Future used ??? since we kan have 3-levels 
	//with some modules (espedsgstats calling espedsgtvinnsad for example)
	public SessionCookieManager(HttpServletRequest request){ 
		//this should be uncomment if we change localCookie-Strategy
		//this.tokenId2Suffix = this.getLocalCookieTokenSuffix(request);
		this.httpsProtocol = request.getRequestURL().toString().contains("https://");
	}
	
	/**
	 * 
	 * @param cookieName
	 * @param cookieValue
	 * @param response
	 */
	public void addGlobalCookieToken(String cookieValue, HttpServletResponse response){
		
		Cookie cookie = new Cookie(this.tokenId1, cookieValue);
    	//removed to avoid time-out--->cookie.setMaxAge(TIME_OUT_VALUE_IN_SECONDS);
    	cookie.setHttpOnly(true);
    	//web.xml has not this as default since we must be able to handle http (not secure) as a fall-back
    	if(this.httpsProtocol){
    		cookie.setSecure(true);
    	}	
    	//global cookie accessible every where
    	cookie.setPath("/");
    	logger.warn(cookie);
    	response.addCookie(cookie);
		
	}
	/**
	 * 
	 * @param cookieValue
	 * @param response
	 */
	public void addLocalCookieToken(String cookieValue, HttpServletResponse response){
		String cookieName = this.tokenId2;
		if(!this.tokenId2Suffix.equals("")){ cookieName=this.tokenId2 + this.tokenId2Suffix; }
		
		Cookie cookie = new Cookie(cookieName, cookieValue);
    	////removed to avoid time-out--->cookie.setMaxAge(TIME_OUT_VALUE_IN_SECONDS);
    	cookie.setHttpOnly(true);
    	//web.xml has not this as default since we must be able to handle http (not secure) as a fall-back
    	if(this.httpsProtocol){
    		cookie.setSecure(true);
    	}	
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
		
		if(request!=null && request.getCookies()!=null){
			for(Cookie cookie : request.getCookies()){
				//logger.warn(cookie.getName());
				if(cookie.getName().equals(this.tokenId1)){
					retval = this.aesManager.decryptBearer(cookie.getValue());
				}
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
			logger.warn(cookie.getName());
			//default
			String cookieName = this.tokenId2;
			if(!this.tokenId2Suffix.equals("")){ cookieName=this.tokenId2 + this.tokenId2Suffix; }
			
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
	public void removeGlobalCookie(HttpServletResponse response){
		Cookie cookie = new Cookie(this.tokenId1, null);
    	cookie.setMaxAge(0);
    	cookie.setHttpOnly(true);
    	// global cookie accessible every where
    	cookie.setPath("/"); 
    	response.addCookie(cookie);
		
	}
	/**
	 * 
	 * @param response
	 */
	public void removeLocalCookie(HttpServletResponse response){
		String cookieName = this.tokenId2;
		if(!this.tokenId2Suffix.equals("")){ cookieName=this.tokenId2 + this.tokenId2Suffix; }
		
		Cookie cookie = new Cookie(cookieName, null);
    	cookie.setMaxAge(0);
    	cookie.setHttpOnly(true);
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
		SystemaWebUser appUser = null;
		String sessionId = null;
		
		try{
			if(request!=null && request.getSession() !=null ){
				appUser = (SystemaWebUser)request.getSession().getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
				sessionId = request.getSession().getId();
				//
				if(cookie!=null && appUser!=null){
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
		
		if(this.isValidForLocalCookies(request)){
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
		if(request.getCookies()!=null){
			for (Cookie cookie : request.getCookies()) {
			    cookie.setValue("");
			    cookie.setMaxAge(0);
			    cookie.setPath("/");
			    response.addCookie(cookie);
			}
		}
	}
	
	/**
	 * This method returns those URIs required to have a localCookie.
	 * (1) espedsg2 is the parent globalCookie and therefore not valid for a localCookie
	 * 
	 * @param request
	 * @return
	 */
	public boolean isValidForLocalCookies(HttpServletRequest request){
		boolean retval = true;
		if(request.getRequestURI().startsWith(this.getENTRY_MODULE_PATH_ESPEDSG2())){
    		retval = false;
    	}
		
		return retval;
	}
	
	/**
	 * Gives a unique identifier to a specific local token. All local token start with TUID2.
	 * The suffix will give a unique name e.g.: TUID2_espedsg2, TUID2_espedsgmmaint, TUID2_espedsgtds, etc
	 * 
	 * To be used instead for TUID2 alone. Under review !!!
	 * 
	 * @param request
	 * @return
	 */
	public String getLocalCookieTokenSuffix(HttpServletRequest request){
		String suffixSeparator = "_";
		String retval = "";
		
		String value = request.getRequestURI();
		//now search for the second "/" (ergo: 0+1) in string: "/espedsgmmaint/..." for example 
		int index = value.indexOf("/", 1);
		retval = suffixSeparator + value.substring(1, index);

		return retval;
	}
}
