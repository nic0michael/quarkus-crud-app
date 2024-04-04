package za.co.nico.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.nico.entities.CmEnrichedDisplayData;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;
import java.util.HashMap;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnrichedDisplayMessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(EnrichedDisplayMessageService.class);

	/**
	 * This method calls the Camel Freemarker API 
	 * It should use String messageBody field in MessageDto as Freemarker Template
	 * And use Map<String, Object> mapPayload field in MessageDto as Freemarker  Data Model
	 * It should produce the field : String enrichedMessageBody in MessageDto
	 * containing the enriched HTML
	 */
	public void enrichMessageBody(CmEnrichedDisplayData cmEnrichedDisplayData ) {
		logger.info("enrichMessageBody called");
		

        String cmMapPayloadJson = cmEnrichedDisplayData.getCmDataContent();
        logger.info("CmMapPayloadJson :" + cmMapPayloadJson);
        try {
            // Convert JSON string to Map<String, Object>
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String, Object> dataModel = objectMapper.readValue(cmMapPayloadJson, HashMap.class);

            // Freemarker configuration
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // Freemarker template
            String cmTemplateContent = cmEnrichedDisplayData.getCmTemplateContent();
            logger.info("CmTemplateContent : " + cmTemplateContent);
            Template template = new Template("template", cmTemplateContent, cfg);

            // Merge template with data model
            StringWriter stringWriter = new StringWriter();
            template.process(dataModel, stringWriter);

            // Get the merged output
            String enrichedBody = stringWriter.toString();
            logger.info("enrichedBody: {}", enrichedBody);

            // Set the enriched body to the CmEnrichedDisplayData
            cmEnrichedDisplayData.setCmEnrichedDisplayDataContent(enrichedBody);

        } catch (Exception e) {
            logger.error("Failed to enrich the message body", e);
        }


	}


}
