package za.co.nico.services;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.enterprise.util.TypeLiteral;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;

import java.util.Map;
import za.co.nico.dtos.MessageDto;
import za.co.nico.entities.CmEnrichedDisplayData;
import za.co.nico.entities.CmSampleData;
import za.co.nico.entities.CmTemplate;
import za.co.nico.repos.CmEnrichedDisplayDataRepo;

@ApplicationScoped
public class CmEnrichedDisplayDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(CmEnrichedDisplayDataService.class);
			
	@Inject
	CmEnrichedDisplayDataRepo repoCmEnrichedDisplayDataRepo;
	
	@Inject
	EnrichedDisplayMessageService messageRouterService;

	public CmEnrichedDisplayData findByCmTemplateName(String cmTemplateName) throws Exception{
		return repoCmEnrichedDisplayDataRepo.findByCmTemplateName(cmTemplateName);
	}

	@Transactional
	public CmEnrichedDisplayData createCmEnrichedDisplayData(CmTemplate cmTemplate, CmSampleData cmSampleData) throws Exception {
		logger.info("createCmEnrichedDisplayData called");
		
		String cmTemplateName = cmTemplate.getCmTemplateName();
		String cmTemplateContent = cmTemplate.getCmTemplateContent();
		logger.info("cmTemplateContent : "+cmTemplateContent);
		LocalDateTime creationDate = cmTemplate.getCreationDate();
	    String changedBy = cmTemplate.getChangedBy();
		String cmDataContent = cmSampleData.getCmMapPayloadJson();
	    String mapPayloadJson = cmSampleData.getCmMapPayloadJson();
	    logger.info("mapPayloadJson : "+mapPayloadJson);
		
		CmEnrichedDisplayData foundCmEnrichedDisplayData = repoCmEnrichedDisplayDataRepo.findByCmTemplateName(cmTemplateName);
		if(null == foundCmEnrichedDisplayData) {
			foundCmEnrichedDisplayData = new CmEnrichedDisplayData();
		}
		
	    foundCmEnrichedDisplayData.setCreationDate(creationDate);
	    foundCmEnrichedDisplayData.setCmTemplateName(cmTemplateName);
	    foundCmEnrichedDisplayData.setCreationDate(creationDate);
	    foundCmEnrichedDisplayData.setChangedBy(changedBy);
	    foundCmEnrichedDisplayData.setCmDataContent(cmDataContent);
	    foundCmEnrichedDisplayData.setCmTemplateContent(cmTemplateContent);
		    
	    messageRouterService.enrichMessageBody(foundCmEnrichedDisplayData );	    
//	    String enrichedBody = foundCmEnrichedDisplayData.getCmEnrichedDisplayDataContent();	
//	    foundCmEnrichedDisplayData.setCmEnrichedDisplayDataContent(enrichedBody);
   	    
	    logger.info("foundCmEnrichedDisplayData : "+foundCmEnrichedDisplayData);
	    try {
			repoCmEnrichedDisplayDataRepo.persist(foundCmEnrichedDisplayData);
			logger.info("Persisted foundCmEnrichedDisplayData : "+foundCmEnrichedDisplayData);
		} catch (Exception e) {
			logger.error("Failed to persist foundCmEnrichedDisplayData",e);
			throw new Exception("Failed to persist foundCmEnrichedDisplayData",e);
		}
		return foundCmEnrichedDisplayData;
	}


	private Map<String, Object> jsonToMap(String mapPayloadJson) throws Exception {
		logger.info("jsonToMap called");
		Map<String, Object> jsonToMap = null;
	    Jsonb jsonb = JsonbBuilder.create();
	    jsonToMap = jsonb.fromJson(mapPayloadJson, new TypeLiteral<Map<String, Object>>() {}.getType());
	    logger.info("jsonToMap : size: "+jsonToMap.size());
	    return jsonToMap;
	}


	

}
