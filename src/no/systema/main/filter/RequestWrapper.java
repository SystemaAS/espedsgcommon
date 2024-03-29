package no.systema.main.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.*;

/**
 * 
 * @author oscardelatorre
 * @date Dec - 2019
 */
public final class RequestWrapper extends HttpServletRequestWrapper {
	private static Logger logger = LoggerFactory.getLogger(RequestWrapper.class);
	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	public String[] getParameterValues(String parameter) {
		logger.debug("InarameterValues .. parameter .......");
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		logger.debug("Inarameter .. parameter .......");
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		logger.debug("Inarameter RequestWrapper ........ value .......");
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		logger.debug("Ineader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null)
			return null;
		logger.debug("Ineader RequestWrapper ........... value ....");
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.info("InnXSS RequestWrapper ..............." + value);
		//value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
		//value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
		//value = value.replaceAll("'", "& #39;");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");

		value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
		value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
		value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
		value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
		value = value.replaceAll("script", "");
		//value = value.replaceAll("</script>", "");
		logger.info("OutnXSS RequestWrapper ........ value ......." + value);
		return value;
	}
}