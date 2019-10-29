package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * L1 - KUNDF db table
 * @author oscardelatorre
 *
 */
public class JsonMaintMainKundfRecord extends JsonAbstractGrandFatherRecord {

	
	private String kundnr = null;                                
	public void setKundnr (String value){ this.kundnr = value;   }   
	public String getKundnr (){ return this.kundnr;   }              

	private String firma = null;                                
	public void setFirma (String value){ this.firma = value;   }   
	public String getFirma (){ return this.firma;   }              

	private String knavn = null;                                
	public void setKnavn (String value){ this.knavn = value;   }   
	public String getKnavn (){ return this.knavn;   }              

	private String adr1 = null;                                
	public void setAdr1 (String value){ this.adr1 = value;   }   
	public String getAdr1 (){ return this.adr1;   }              

	private String adr2 = null;                                
	public void setAdr2 (String value){ this.adr2 = value;   }   
	public String getAdr2 (){ return this.adr2;   }              

	private String adr3 = null;                                
	public void setAdr3 (String value){ this.adr3 = value;   }   
	public String getAdr3 (){ return this.adr3;   }              

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
	public void setSonavn (String value){ this.sonavn = value;   }   
	public String getSonavn (){ return this.sonavn;   }              

	
	private String spraak = null;                                
	public void setSpraak (String value){ this.spraak = value;   }   
	public String getSpraak (){ return this.spraak;   }              

	private String kpers = null;                                
	public void setKpers (String value){ this.kpers = value;   }   
	public String getKpers(){ return this.kpers;   }    	
	
	private String tlf = null;                                
	public void setTlf (String value){ this.tlf = value;   }   
	public String getTlf(){ return this.tlf;   }    	

	private String valkod = null;                                
	public void setValkod (String value){ this.valkod = value;   }   
	public String getValkod(){ return this.valkod;   }    	


	private String bankg = null;                                
	public void setBankg (String value){ this.bankg = value;   }   
	public String getBankg(){ return this.bankg;   }    	

	private String postg = null;                                
	public void setPostg (String value){ this.postg = value;   }   
	public String getPostg(){ return this.postg;   }    	
	
	private String betbet = null;                                
	public void setBetbet (String value){ this.betbet = value;   }   
	public String getBetbet(){ return this.betbet;   }    	
	
	private String betmat = null;                                
	public void setBetmat (String value){ this.betmat = value;   }   
	public String getBetmat(){ return this.betmat;   }    	
	
	private String sfakt = null;                                
	public void setSfakt (String value){ this.sfakt = value;   }   
	public String getSfakt(){ return this.sfakt;   }    
	
	
	private String dkund = null;                                
	public void setDkund(String value){ this.dkund = value;   }   
	public String getDkund(){ return this.dkund;   }   

	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
}
