package no.systema.main.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.*;

/**
 * 
 * @author oscardelatorre
 * @date Dec - 2019
 */
public class CrossScriptingFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(CrossScriptingFilter.class);
    private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{
    	try{
    		logger.info("Inlter CrossScriptingFilter  ...");
    		chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
    		logger.info("Outlter CrossScriptingFilter ...");
    	}catch(Exception e){
    		logger.error(e.toString());
    		throw e;
    	}
    }

}