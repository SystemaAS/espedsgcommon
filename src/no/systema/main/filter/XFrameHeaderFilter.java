package no.systema.main.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class XFrameHeaderFilter implements Filter {
	private static Logger logger = Logger.getLogger(XFrameHeaderFilter.class);
    private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }
	
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException {
        try{
        	((HttpServletResponse) resp).setHeader("x-frame-options", "deny");
        	logger.info("XFrame-filter ...");
        	chain.doFilter(req, resp);
        }catch(Exception e){
    		logger.error(e.toString());
    	}
    }
}
