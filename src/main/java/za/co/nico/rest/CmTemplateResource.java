package za.co.nico.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

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
import za.co.nico.entities.CmTemplate;
import za.co.nico.exceptions.InvalidTemplateException;
import za.co.nico.services.CmTemplateService;
import za.co.nico.type.APIResponses;

@Path("templates")
public class CmTemplateResource {
	private static Logger logger = LoggerFactory.getLogger(CmTemplateResource.class);

	@Inject
	CmTemplateService cmTemplateService;

	/**
	 * Open in Browser : http://localhost:8080/message-api/templates/name
	 * 
	 */
	@GET
	@Path("/name")
	@Produces(MediaType.TEXT_PLAIN)
	public String getName() {
		return "nico";
	}


    @GET
    @Path("/cmTemplateOwnerNames")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCmTemplateOwnerNames() {
        try {
            List<String> cmTemplateOwnerNames = cmTemplateService.cmTemplateOwnerNames();
            return Response.ok(cmTemplateOwnerNames).build();
        } catch (Exception e) {
	        return Response.status(Status.NOT_FOUND)
                    .entity(APIResponses.APIResponseMessage.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Path("/listCmTemplateNames/{cmTemplateOwnerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCmTemplateNames(@PathParam("cmTemplateOwnerName") String cmTemplateOwnerName) {
        try {
            List<String> cmTemplateNames = cmTemplateService.listCmTemplateNames(cmTemplateOwnerName);
            return Response.ok(cmTemplateNames).build();
        } catch (Exception e) {
	        return Response.status(Status.NOT_FOUND)
                    .entity(APIResponses.APIResponseMessage.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Path("/listCmTemplatesBy/{cmTemplateOwnerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllByCmTemplateOwnerName(@PathParam("cmTemplateOwnerName") String cmTemplateOwnerName) {
        try {
            List<CmTemplate> cmTemplates = cmTemplateService.listAllByCmTemplateOwnerName(cmTemplateOwnerName);
            return Response.ok(cmTemplates).build();
        } catch (Exception e) {
	        return Response.status(Status.NOT_FOUND)
                    .entity(APIResponses.APIResponseMessage.NOT_FOUND)
                    .build();
        }
    }
	
	@GET
	@Path("/{cmTemplateName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByCmSampleDataName(@PathParam("cmTemplateName") String cmTemplateName) {
	    try {
	    	CmTemplate cmTemplate = cmTemplateService.findByCmTemplateName(cmTemplateName);
	        return Response.status(APIResponses.APIResponseCodes.SUCCESS)
	                       .entity(cmTemplate)
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
	        CmTemplate createdTemplate = cmTemplateService.createCmTemplate(cmTemplate);
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

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCmTemplate(CmTemplate cmTemplate) {
	    try {
	        CmTemplate updatedTemplate = cmTemplateService.updateCmTemplate(cmTemplate);
	        return Response.ok(updatedTemplate).build();
	    } catch (InvalidTemplateException e) {
	        return Response.status(APIResponses.APIResponseCodes.INVALID_PARMS)
	                       .entity(APIResponses.APIResponseMessage.UPDATED_FAILED)
	                       .build();
	    } catch (Exception e) {
	        return Response.status(Status.INTERNAL_SERVER_ERROR)
	                       .entity(APIResponses.APIResponseMessage.UPDATED_FAILED)
	                       .build();
	    }
	}


	@DELETE
	@Path("/{cmTemplateName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCmTemplateByCmTemplateName(@PathParam("cmTemplateName") String cmTemplateName) {
	    try {
	        cmTemplateService.deleteCmTemplateByCmTemplateName(cmTemplateName);
	        return Response.status(APIResponses.APIResponseCodes.DELETED)
	                       .entity(APIResponses.APIResponseMessage.DELETED)
	                       .build();
	    } catch (Exception e) {
	        return Response.status(Status.INTERNAL_SERVER_ERROR)
	                       .entity(APIResponses.APIResponseMessage.DELETED)
	                       .build();
		}
	}

	
}
