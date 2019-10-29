package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * L1 - json container dto
 * @author oscardelatorre
 *
 * Oct 2019
 */
@Data
public class JsonMaintMainKundfContainer {

	
	private String user;                                
	private String errMsg;
	
	private Collection<JsonMaintMainKundfRecord> list;
	public void setList(Collection<JsonMaintMainKundfRecord> value){ this.list = value; }
	public Collection<JsonMaintMainKundfRecord> getList(){ return list; }
	
}
