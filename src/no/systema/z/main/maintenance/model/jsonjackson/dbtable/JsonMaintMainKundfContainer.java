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
	private String kundnr; 
	private String firma;
	private String l1_Kundnr;	//place holder for kundnr (when update)
	
	private String l1_Head;		//Språk Header/ledetekster (1)
	private String l1_KundGr;	//Kundegruppe (2)
	private String l1_Feks;		//Ant Faktura (1) integer
	private String l1_Pkod;		//Purrekode (1) "J" vid init
	private String l1_Kutdr;	//KontoUtdr.Kode (1) "J" vid init
	private String l1_Khenv;	//KontoHenvisning (3)
	private String l1_Pgebyr;	//PurreGebyr (1)
	private String l1_DaoAar;	//Dato opprettet År (4) number
	private String l1_DaoMnd;	//Dato opprettet Mnd (2) number
	private String l1_DaoDag;	//Dato opprettet Dag (2) number
	private String l1_Rkod; //Rentekode
	private String l1_Ledi20; //Dato Oppr
	
	private String errMsg;
	
	

	private Collection<JsonMaintMainKundfRecord> list;
	public void setList(Collection<JsonMaintMainKundfRecord> value){ this.list = value; }
	public Collection<JsonMaintMainKundfRecord> getList(){ return list; }
	
	private Collection<JsonMaintMainKundfRecordL1AuxList> l1_KhenvList;
	public void setL1_KhenvList(Collection<JsonMaintMainKundfRecordL1AuxList> value){ this.l1_KhenvList = value; }
	public Collection<JsonMaintMainKundfRecordL1AuxList> getL1_KhenvList(){ return l1_KhenvList; }
	
	private Collection<JsonMaintMainKundfRecordL1AuxList> l1_KgrupList;
	public void setL1_KgrupList(Collection<JsonMaintMainKundfRecordL1AuxList> value){ this.l1_KgrupList = value; }
	public Collection<JsonMaintMainKundfRecordL1AuxList> getL1_KgrupList(){ return l1_KgrupList; }
	
	
}
