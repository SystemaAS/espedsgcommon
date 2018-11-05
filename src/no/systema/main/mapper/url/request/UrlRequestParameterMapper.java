/**
 * 
 */
package no.systema.main.mapper.url.request;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
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

	private static Logger logger = Logger.getLogger(UrlRequestParameterMapper.class.getName());
	
	
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
	 * Parsing object implementing {@linkplain IDao} into {@linkplain MultiValueMap}.
	 * Using {@link ReflectionUtils}
	 * @param daoObj
	 * @return a MultiValueMap with name:value to use in uri.
	 */
	public static MultiValueMap<String, String> getUriParameter(Object daoObj) {
		MultiValueMap<String, String> recordParams = new LinkedMultiValueMap<>();

		try {
			String className = daoObj.getClass().getName();
			Class<?> clazz = Class.forName(className);
			Object dao = (Object) clazz.newInstance();

			logger.info("dao="+dao);
			Object parent = (Object) clazz.getSuperclass().newInstance();
			logger.info("parent="+parent);			
			
			Class cl = Class.forName(dao.getClass().getCanonicalName());
			Field[] fields = cl.getDeclaredFields();

			for (Field field : fields) {
				ReflectionUtils.makeAccessible(field);
				String name = (String) field.getName();
				if ("keys".equals(name)) {
					continue;
				}
				Object value = ReflectionUtils.getField(field, daoObj );
				logger.info("getUriParameter: name="+name+", value="+value);
				if (value != null) {
					recordParams.add(name, String.valueOf(value).trim());
				}
			}
		} catch (Exception e) {
			String errMsg = String.format("Error running reflection on dao: %s", daoObj);
			logger.error(errMsg, e);
			throw new RuntimeException(errMsg);
		} 
		
		return recordParams;

	}
	
}
