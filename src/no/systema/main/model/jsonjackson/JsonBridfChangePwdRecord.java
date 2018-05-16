package no.systema.main.model.jsonjackson;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * 
 * @author oscardelatorre
 * @date Maj 16, 2018
 * 
 */
public class JsonBridfChangePwdRecord extends JsonAbstractGrandFatherRecord implements Serializable {
	
	private String bibrid = null;                                
	public void setBibrid (String value){ this.bibrid = value;   }   
	public String getBibrid (){ return this.bibrid;   }              

	private String bipo = null;                                
	public void setBipo (String value){ this.bipo = value;   }   
	public String getBipo (){ return this.bipo;   }              

	private String bibesk = null;                                
	public void setBibesk (String value){ this.bibesk = value;   }   
	public String getBibesk (){ return this.bibesk;   }              

	/**
	 * Required for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
