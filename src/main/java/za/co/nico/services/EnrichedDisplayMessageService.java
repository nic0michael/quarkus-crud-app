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
	 * It uses String cmTemplateContent field in cmEnrichedDisplayData as Freemarker Template
	 * And usesString cmMapPayloadJson field in cmEnrichedDisplayData as Freemarker  Data Model
	 * It produces in the field : String cmEnrichedDisplayDataContent in cmEnrichedDisplayData
	 * the enriched HTML
	 */
	public void enrichMessageBody(CmEnrichedDisplayData cmEnrichedDisplayData ) {
		logger.info("enrichMessageBody called");
		

        String cmMapPayloadJson = cmEnrichedDisplayData.getCmDataContent();
        logger.info("CmMapPayloadJson :" + cmMapPayloadJson);
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String, Object> dataModel = objectMapper.readValue(cmMapPayloadJson, HashMap.class);

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            String cmTemplateContent = cmEnrichedDisplayData.getCmTemplateContent();
            logger.info("CmTemplateContent : " + cmTemplateContent);
            Template template = new Template("template", cmTemplateContent, cfg);

            StringWriter stringWriter = new StringWriter();
            template.process(dataModel, stringWriter);

            String enrichedBody = stringWriter.toString();
            logger.info("enrichedBody: {}", enrichedBody);

            cmEnrichedDisplayData.setCmEnrichedDisplayDataContent(enrichedBody);

        } catch (Exception e) {
            logger.error("Failed to enrich the message body", e);
        }


	}


}
