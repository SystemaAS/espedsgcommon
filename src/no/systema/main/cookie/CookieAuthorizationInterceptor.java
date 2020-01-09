package no.systema.main.cookie;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;

/**
 * This class executes at every entry-point to every request mapping.It has some exception (exclude-mappings... refer to the applications servlet.xml)
 * It could be additional checks in a specific request mapping (Controller) due to checks on a specific user (other than session user).
 * 
 *
 * @author oscardelatorre
 * @date Dec 2019
 * 
 */
public class CookieAuthorizationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getLogger(CookieAuthorizationInterceptor.class.getName());
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		SessionCookieManager cookieMgr = new SessionCookieManager(request);
		//default cookie
		Map<String,String> cookie = cookieMgr.getGlobalCookieToken(request);
		logger.warn(request.getRequestURI());
    	//logger.warn(cookie);
    	if(cookieMgr.isValidForLocalCookies(request)){
    		//when moving to another module (.war)
    		cookie = cookieMgr.getLocalCookieToken(request);
    		//logger.warn(cookie);
    	}
    	
    	
    	boolean isAuthorized = false;
    	try{
    		//(1) Check first if the user is already logged in
    			if(cookie!=null){
	    			isAuthorized = cookieMgr.isAuthorized(cookie, request);
	    		}
    	
    	}catch(Exception e){
    		logger.warn(e.toString());
    	}
		
	    return isAuthorized;
	}
	
}
