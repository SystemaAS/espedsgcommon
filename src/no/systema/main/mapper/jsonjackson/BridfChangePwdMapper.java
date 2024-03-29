/**
 * 
 */
package no.systema.main.mapper.jsonjackson;

//jackson library
import org.slf4j.*;


import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.main.model.jsonjackson.JsonBridfChangePwdContainer;
import no.systema.main.model.jsonjackson.JsonBridfChangePwdRecord;

//java lib
import java.util.*;

/**
 * General mapper to the main package (Systema Web eSped)
 * 
 * @author oscardelatorre
 * 
 */
public class BridfChangePwdMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = LoggerFactory.getLogger(BridfChangePwdMapper.class.getName());
	
	public JsonBridfChangePwdContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonBridfChangePwdContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonBridfChangePwdContainer.class); 
		//logger.info("Mapping currency rate object from JSON payload...");
		//logger.info("[JSON-String payload status=OK]  " + systemaUserContainer.getUser());
		
		//DEBUG
		Collection<JsonBridfChangePwdRecord> fields = container.getList();
		for(JsonBridfChangePwdRecord record : fields){
			//logger.info("Currency factor: " + record.getSvvs_omr());
			//logger.info("Currency rate: " + record.getSvvk_krs());
		}
			
		return container;
	}
}
