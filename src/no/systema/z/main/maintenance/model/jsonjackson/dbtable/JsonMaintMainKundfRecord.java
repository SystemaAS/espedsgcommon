package no.systema.z.main.maintenance.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

/**
 * L1 - KUNDF db table only delta fields from existing fields in Cundf
 * @author oscardelatorre
 * Nov, 2019
 */
@Data
public class JsonMaintMainKundfRecord extends JsonMaintMainCundfRecord {
	
	//Delta
	private String land;
	private String head;
	private String feks;
	private String pkod;
	private String pgebyr;
	private String daoaar;
	private String daomnd;
	private String daodag;
	//as Cundf but instead of inherited so that modelmapper works with one line
	
	
	
	private String kundetype;                                
	private String kundnr;                                
	private String firma;                                
	private String knavn;                                
	private String adr1;                                
	private String adr2;                                
	private String adr3;                                
	private String postnr;                                
	public void setPostnr (String value){ this.postnr = value;   }   
	public String getPostnr (){ 
		if ("0".equals(postnr)) {
			return "";
		} else {
			return this.postnr;
		}
	}              

	private String syrg;                                
	private String syland;                                
	private String sykont;                                
	public void setSykont (String value){ this.sykont = value;   }   
	public String getSykont (){ 
		if ("0".equals(sykont)) {
			return "";
		} else {
			return this.sykont;
		}
	}              

	private String syfr02;                                
	private String sonavn;                                
	private String sypoge;                                
	private String spraak;                                
	private String eori;                                
	private String pnpbku;                                
	private String kpers;                                
	private String tlf;                                
	private String syepos;                                
	private String systat;                                
	private String valkod;                                
	private String kundgr;                                
	private String adr21;                                
	private String fmot;                                
	private String fmotname;                                
	private String bankg;                                
	private String postg;                                
	private String betbet;                                
	private String betmat;                                
	private String sfakt;                                
	private String kgrens;                                
	private String sysalu;                                
	private String syfr03;                                
	private String xxinm3;                                
	private String xxinlm;                                
	private String rnraku;                                
	private String symvjn;                                
	private String symvsp;                                
	private String syutlp;                                
	private String syminu;                                
	private String syopdt;                                
	private String sylikv;                                
	private String golk;                                
	private String aktkod;                                
	private String dkund;                                
	private String vatkku;                                
	private String syselg;                                
	private String aknrku;                                
	private String syregn;
	
	/*
	private String syfr04;                                
	private String syfr05;                                
	private String syfr06;                                
	private String xxbre;                                
	public void setXxbre(String value){ this.xxbre = value;   }   
	public String getXxbre(){ 
		if ("0.000000".equals(xxbre)) {
			return "";
		} else {
			return this.xxbre;
		}		
	}   		
	private String syiat1;                                
	private String xxlen;                                
	public void setXxlen(String value){ this.xxlen = value;   }   
	public String getXxlen(){ 
		if ("0.000000".equals(xxlen)) {
			return "";
		} else {
			return this.xxlen;
		}
	}  
	
	private String syiat2;                                
	private String mllm;                                
	private String m3m3;                                
	private String elma;                                
	//CUNDC
	private String epost;                                
	private String epostmott;                                
	
	//VADR
	private String vadrnr;                                
	private String vadrna;                                
	private String vadrn1;                                
	private String vadrn2;                                
	private String vadrn3;                                
	private String valand;                                
	*/

}
