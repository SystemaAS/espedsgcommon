package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * L1 - KUNDF db table
 * @author oscardelatorre
 *
 */
@Data
public class JsonMaintMainKundfRecord extends JsonAbstractGrandFatherRecord {

	private String kundnr = null;                                
	private String firma = null;                                
	private String knavn = null;                                
	private String adr1 = null;                                
	private String adr2 = null;                                
	private String adr3 = null;                                
	
	private String postnr = null;                                
	public void setPostnr (String value){ this.postnr = value;   }   
	public String getPostnr (){ 
		if ("0".equals(postnr)) {
			return "";
		} else {
			return this.postnr;
		}
	}              

	private String sonavn = null;                                
	private String spraak = null;                                
	private String kpers = null;                                
	private String tlf = null;                                
	private String valkod = null;                                
	private String bankg = null;                                
	private String postg = null;                                
	private String betbet = null;                                
	private String betmat = null;                                
	private String sfakt = null;                                
	private String dkund = null;                                
	private String selger = null;                                
	
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
