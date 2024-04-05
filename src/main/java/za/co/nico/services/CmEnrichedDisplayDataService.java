package za.co.nico.services;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
	private static final String DOT_FTL = ".ftl";
			
	@Inject
	CmEnrichedDisplayDataRepo repoCmEnrichedDisplayDataRepo;
	
	@Inject
	EnrichedDisplayMessageService enrichedDisplayMessageService;

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
		    
	    enrichedDisplayMessageService.enrichMessageBody(foundCmEnrichedDisplayData );	    
//	    String enrichedBody = foundCmEnrichedDisplayData.getCmEnrichedDisplayDataContent();	
//	    foundCmEnrichedDisplayData.setCmEnrichedDisplayDataContent(enrichedBody);
	    String freemarkerTemplateName = foundCmEnrichedDisplayData.getCmTemplateName();
	    String freemarkerTemplateContents = foundCmEnrichedDisplayData.getCmTemplateContent();
   	    
	    logger.info("foundCmEnrichedDisplayData : "+foundCmEnrichedDisplayData);
	    try {
			repoCmEnrichedDisplayDataRepo.persist(foundCmEnrichedDisplayData);
			logger.info("Persisted foundCmEnrichedDisplayData : "+foundCmEnrichedDisplayData);
			writeFreemarkerTemplateToDisk(freemarkerTemplateName , freemarkerTemplateContents);
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



	/**
	 * This method creates a file with name : cmTemplateName + FTL
	 * In folder src/main/resources/templates/freemarker
	 * with content freemarkerTemplateContents
	 * 
	 */
	private void writeFreemarkerTemplateToDisk(String cmTemplateName, String freemarkerTemplateContent) {
		String freemarkerTemplateFileName = cmTemplateName + DOT_FTL;
	    String folderPath = "src/main/resources/templates/freemarker/";
	    FileWriter writer = null;
	    File file = null;

	    try {
	        File folder = new File(folderPath);
	        if (!folder.exists()) {
	            folder.mkdirs();
	        }
	        
	        file = new File(folderPath + freemarkerTemplateFileName);
	        
	        if (file.exists()) {
	            file.delete();
	        }

	        file.createNewFile();	        
	        writer = new FileWriter(file);
	        writer.write(freemarkerTemplateContent);


	        logger.info("Freemarker template file created: " + file.getAbsolutePath());
	    } catch (IOException e) {
	        logger.error("Failed to write Freemarker template file", e);
	        
	    } finally {
	        if (writer != null) {
	            try {
	                writer.close();
	            } catch (IOException e) {
	                logger.error("Failed to close FileWriter", e);
	            }
	        }
	    } 
	}

	

}
