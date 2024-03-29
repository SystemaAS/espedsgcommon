/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable;

//jackson library
import org.slf4j.*;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtaRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Aug 3, 2016
 * 
 */
public class MaintMainKodtaMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainKodtaMapper.class.getName());
	
	public JsonMaintMainKodtaContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainKodtaContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintMainKodtaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainKodtaRecord> list = container.getList();
		for(JsonMaintMainKodtaRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
