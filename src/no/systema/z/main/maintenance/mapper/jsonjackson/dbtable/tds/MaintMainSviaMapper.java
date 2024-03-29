/**
 * 
 */
package no.systema.z.main.maintenance.mapper.jsonjackson.dbtable.tds;

//jackson library
import org.slf4j.*;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
//application library
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSviaContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.tds.JsonMaintMainSviaRecord;
//
import java.util.*;

/**
 * @author oscardelatorre
 * @date Jun 13, 2017
 * 
 */
public class MaintMainSviaMapper extends ObjectMapperAbstractGrandFather {
	private static final Logger logger = LoggerFactory.getLogger(MaintMainSviaMapper.class.getName());
	
	public JsonMaintMainSviaContainer getContainer(String utfPayload) throws Exception{
		
		//At this point we now have an UTF-8 payload
		JsonMaintMainSviaContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintMainSviaContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintMainSviaRecord> list = container.getList();
		for(JsonMaintMainSviaRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
