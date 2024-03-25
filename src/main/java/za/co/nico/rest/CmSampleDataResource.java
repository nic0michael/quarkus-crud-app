package za.co.nico.rest;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.PathParam;
import jakarta.inject.Inject;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import za.co.nico.entities.CmSampleData;
import za.co.nico.exceptions.InvalidSampleDataException;
import za.co.nico.services.CmSampleDataService;

@Path("/message-api/sampledata")
public class CmSampleDataResource {
	private static Logger logger = LoggerFactory.getLogger(CmSampleDataResource.class);
	
	@Inject
	CmSampleDataService cmSampleDataService;

    @GET
    @Path("/{cmTemplateName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCmSampleDataName(@PathParam("cmTemplateName") String cmTemplateName ) {
        try {
            CmSampleData cmSampleData = cmSampleDataService.findByCmTemplateName(cmTemplateName);
            return Response.ok(cmSampleData).build();
        } catch (Exception e) {
            return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
	

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCmSampleData(CmSampleData cmSampleData) {
        try {
            CmSampleData createdTemplate = cmSampleDataService.createCmSampleData(cmSampleData);
            return Response.status(Status.CREATED).entity(createdTemplate).build();
        } catch (InvalidSampleDataException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
	

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCmSampleData(CmSampleData cmSampleData) {
        try {
            CmSampleData updatedTemplate = cmSampleDataService.updateCmSampleData(cmSampleData);
            return Response.ok(updatedTemplate).build();
        } catch (InvalidSampleDataException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
	

	   @DELETE
	    @Path("/{cmTemplateName}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response deleteCmSampleDataByCmTemplateName(@PathParam("cmTemplateName") String cmTemplateName ) {
	        try {
	            cmSampleDataService.deleteCmSampleDataByCmTemplateName(cmTemplateName);
	            return Response.noContent().build();
	        } catch (Exception e) {
	            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	        }
	    }
}
