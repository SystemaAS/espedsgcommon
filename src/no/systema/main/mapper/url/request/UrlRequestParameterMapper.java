/**
 * 
 */
package no.systema.main.mapper.url.request;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.apache.logging.log4j.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import no.systema.jservices.common.dao.IDao;
import no.systema.jservices.common.util.StringUtils;
import no.systema.main.model.jsonjackson.JsonSystemaUserRecord;
import no.systema.main.util.AppConstants;

/**
 * @author oscardelatorre
 * @param Feb 19, 2013
 */
public class UrlRequestParameterMapper {

	private static Logger logger = LogManager.getLogger(UrlRequestParameterMapper.class.getName());
	
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 * 
	 */
	public String getUrlParameterValidString(JsonSystemaUserRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				field.setAccessible(true);//we must do this in order to access private fields
				sb.append(AppConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
				String value = (String)field.get(object); 
				if(value==null){
					sb.append("");
				}else{
					sb.append(value);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * Parsing object  into {@linkplain MultiValueMap}.
	 * Using {@link ReflectionUtils}
	 * @param object
	 * @return a MultiValueMap with name:value to use in uri.
	 */
	public static MultiValueMap<String, String> getUriParameter(Object object) {
		MultiValueMap<String, String> recordParams = new LinkedMultiValueMap<>();

		try {
			//Class
			String objClassName = object.getClass().getName();
			Class<?> objClazz = Class.forName(objClassName);
			Object objInstance = (Object) objClazz.newInstance();
			logger.info("objInstance="+objInstance);

			Class objInstClass = Class.forName(objInstance.getClass().getCanonicalName());
			Field[] objInstfields = objInstClass.getDeclaredFields();
			for (Field field : objInstfields) {
				ReflectionUtils.makeAccessible(field);
				String name = (String) field.getName();
				if ("keys".equals(name)) {
					continue;
				}
				Object value = ReflectionUtils.getField(field, object );
				logger.info("getUriParameter(object): name="+name+", value="+value);
				if (value != null) {
					recordParams.add(name, String.valueOf(value).trim());
				}
			}

			
			//SuperClass
			String superClassName = object.getClass().getSuperclass().getName();
			Class<?> superClazz = Class.forName(superClassName);
			Object superClassInstance = (Object) superClazz.newInstance();
			logger.info("superClassInstance="+superClassInstance);			
			Class superInstClass = Class.forName(superClassInstance.getClass().getCanonicalName());
			Field[] superInstfields = superInstClass.getDeclaredFields();

			for (Field field : superInstfields) {
				ReflectionUtils.makeAccessible(field);
				String name = (String) field.getName();
				if ("keys".equals(name)) {
					continue;
				}
				Object value = ReflectionUtils.getField(field, object );
				logger.info("getUriParameter(superclass): name="+name+", value="+value);
				if (value != null) {
					recordParams.add(name, String.valueOf(value).trim());
				}
			}
			
			
		} catch (Exception e) {
			String errMsg = String.format("Error running reflection on object: %s", object);
			logger.error(errMsg, e);
			throw new RuntimeException(errMsg);
		} 
		
		return recordParams;

	}
	
}
