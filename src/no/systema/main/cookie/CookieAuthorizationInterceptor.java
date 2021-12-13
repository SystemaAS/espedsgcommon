package no.systema.main.cookie;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.*;
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
	private static final Logger logger = LogManager.getLogger(CookieAuthorizationInterceptor.class.getName());
	private final String ENTRY_MODULE_PATH_ESPEDSG2 = "/espedsg2/";
	
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
	   		}else{
	   			logger.warn("unauthorized ...");
	   			response.sendRedirect("login.do");
	   		}
    	}catch(Exception e){
    		logger.warn("unauthorized ...");
   			response.sendRedirect("login.do");
   			logger.warn(e.toString());
    		
    	}
    	logger.warn(isAuthorized);
    	
    	
    	//this redirect is necessary in order to avoid blank sites ... and send the user to the necessary start point (if applicable)
    	if(!isAuthorized){ 
    		if(request.getRequestURI().startsWith(this.ENTRY_MODULE_PATH_ESPEDSG2)){
    			response.sendRedirect("login.do");
    		}else{
    			response.sendRedirect("/espedsg2/dashboard.do");
    		}
    	}
    	
    	return isAuthorized;
    	
	}
	
}
