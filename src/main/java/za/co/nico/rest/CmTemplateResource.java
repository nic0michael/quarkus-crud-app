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
import za.co.nico.entities.CmTemplate;
import za.co.nico.exceptions.InvalidTemplateException;
import za.co.nico.services.CmTemplateService;

@Path("/message-api/templates")
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
	@Path("/{cmTemplateName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByCmTemplateName(@PathParam("cmTemplateName") String cmTemplateName) {
		try {
			CmTemplate cmTemplate = cmTemplateService.findByCmTemplateName(cmTemplateName);
			return Response.ok(cmTemplate).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCmTemplate(CmTemplate cmTemplate) {
		try {
			CmTemplate createdTemplate = cmTemplateService.createCmTemplate(cmTemplate);
			return Response.status(Status.CREATED).entity(createdTemplate).build();
		} catch (InvalidTemplateException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
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
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Path("/{cmTemplateName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCmTemplateByCmTemplateName(@PathParam("cmTemplateName") String cmTemplateName) {
		try {
			cmTemplateService.deleteCmTemplateByCmTemplateName(cmTemplateName);
			return Response.noContent().build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

}
