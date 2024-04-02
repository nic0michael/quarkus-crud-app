package za.co.nico.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import za.co.nico.entities.CmEnrichedDisplayData;
import za.co.nico.entities.CmSampleData;
import za.co.nico.entities.CmTemplate;
import za.co.nico.exceptions.InvalidSampleDataException;
import za.co.nico.exceptions.InvalidTemplateException;
import za.co.nico.services.CmEnrichedDisplayDataService;
import za.co.nico.services.CmSampleDataService;
import za.co.nico.services.CmTemplateService;
import za.co.nico.services.EnrichedDisplayMessageRouterService;
import za.co.nico.type.APIResponses;

@Path("/message-api/enriched")
public class CmEnrichedDisplayDataResource {
	
	private static final Logger logger = LoggerFactory.getLogger(CmEnrichedDisplayDataResource.class);
	

    @Inject
    CmSampleDataService cmSampleDataService;

	@Inject
	CmTemplateService cmTemplateService;
    
	@Inject
	CmEnrichedDisplayDataService cmEnrichedDisplayDataService;
	

	
	@GET
	@Path("/{cmTemplateName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByCmTemplateName(@PathParam("cmTemplateName") String cmTemplateName) {
	    try {
	    	CmEnrichedDisplayData cmEnrichedDisplayData = cmEnrichedDisplayDataService.findByCmTemplateName(cmTemplateName);
	    	
	        return Response.status(APIResponses.APIResponseCodes.SUCCESS)
	                       .entity(cmEnrichedDisplayData)
	                       .build();
	    } catch (Exception e) {
	        return Response.status(Status.NOT_FOUND)
	                       .entity(APIResponses.APIResponseMessage.NOT_FOUND)
	                       .build();
	    }
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCmTemplate(CmTemplate cmTemplate) {
	    try {
	    	if(null == cmTemplate) {
	    		logger.error("CmTemplate is null");
	    	}

	    	String cmTemplateName = cmTemplate.getCmTemplateName();
	    	logger.info("cmTemplateName : "+cmTemplateName);
	    	CmSampleData cmSampleData = cmSampleDataService.findByCmTemplateName(cmTemplateName);
	    	if(null == cmSampleData) {
	    		logger.error("CmSampleData is null");
	    	}
	    	CmEnrichedDisplayData createdTemplate = cmEnrichedDisplayDataService.createCmEnrichedDisplayData(cmTemplate,cmSampleData);
	        return Response.status(APIResponses.APIResponseCodes.CREATED)
	                       .entity(createdTemplate)
	                       .build();
	    } catch (InvalidTemplateException e) {
	        return Response.status(APIResponses.APIResponseCodes.INVALID_PARMS)
	                       .entity(APIResponses.APIResponseMessage.CREATE_FAILED)
	                       .build();
	    } catch (Exception e) {
	        return Response.status(Status.INTERNAL_SERVER_ERROR)
	                       .entity(APIResponses.APIResponseMessage.CREATE_FAILED)
	                       .build();
	    }
	}
	
}
