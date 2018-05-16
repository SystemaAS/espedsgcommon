package no.systema.main.model.jsonjackson;
import java.io.Serializable;
import java.util.Collection;
/**
 * 
 * @author oscardelatorre
 * @date Maj 16, 2018
 * 
 */
public class JsonBridfChangePwdContainer implements Serializable {
	
	private String user = null;                                
	public void setUser (String value){ this.user = value;   }   
	public String getUser (){ return this.user;   }              

	private String errMsg = null;                                
	public void setErrMsg (String value){ this.errMsg = value;   }   
	public String getErrMsg (){ return this.errMsg;   }              

	private Collection<JsonBridfChangePwdRecord> list = null;                                
	public void setList (Collection<JsonBridfChangePwdRecord> value){ this.list = value;   }   
	public Collection<JsonBridfChangePwdRecord> getList (){ return this.list;   }              

	
	
}
