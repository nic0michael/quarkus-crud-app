package za.co.nico.rest;

import java.util.List;

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
import za.co.nico.entities.CmSampleData;
import za.co.nico.exceptions.InvalidSampleDataException;
import za.co.nico.services.CmSampleDataService;
import za.co.nico.type.APIResponses;

@Path("/sampledata")
public class CmSampleDataResource {
    private static Logger logger = LoggerFactory.getLogger(CmSampleDataResource.class);

    @Inject
    CmSampleDataService cmSampleDataService;
    

    @GET
    @Path("/listall/{cmTemplateOwnerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllByCmTemplateOwnerName(@PathParam("cmTemplateOwnerName") String cmTemplateOwnerName) {
        try {
        	logger.info("Called listAllByCmTemplateOwnerName cmTemplateOwnerName : "+cmTemplateOwnerName);
            List<CmSampleData> cmTemplateNames = cmSampleDataService.findAllByCmTemplateOwnerName(cmTemplateOwnerName);
            return Response.ok(cmTemplateNames).build();
        } catch (Exception e) {
	        return Response.status(Status.NOT_FOUND)
                    .entity(APIResponses.APIResponseMessage.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Path("/{cmTemplateName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCmSampleDataName(@PathParam("cmTemplateName") String cmTemplateName ) {
        try {
            CmSampleData cmSampleData = cmSampleDataService.findByCmTemplateName(cmTemplateName);
            return Response.status(APIResponses.APIResponseCodes.SUCCESS)
                           .entity(cmSampleData)
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
    public Response createCmSampleData(CmSampleData cmSampleData) {
        try {
            CmSampleData createdSampleData = cmSampleDataService.createCmSampleData(cmSampleData);
            return Response.status(APIResponses.APIResponseCodes.CREATED)
                           .entity(createdSampleData)
                           .build();
        } catch (InvalidSampleDataException e) {
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
    public Response updateCmSampleData(CmSampleData cmSampleData) {
        try {
            CmSampleData updatedSampleData = cmSampleDataService.updateCmSampleData(cmSampleData);
            return Response.status(APIResponses.APIResponseCodes.SUCCESS)
                           .entity(updatedSampleData)
                           .build();
        } catch (InvalidSampleDataException e) {
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
    public Response deleteCmSampleDataByCmTemplateName(@PathParam("cmTemplateName") String cmTemplateName ) {
        try {
            cmSampleDataService.deleteCmSampleDataByCmTemplateName(cmTemplateName);
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

