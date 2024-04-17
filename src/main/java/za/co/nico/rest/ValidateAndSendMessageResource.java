package za.co.nico.rest;


import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.util.StringUtil;
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

import za.co.nico.dtos.MessageDto;
import za.co.nico.exceptions.InvalidMessageException;
import za.co.nico.services.ValidateSendMessageService;

@Path("/validate-message")
public class ValidateAndSendMessageResource {
	private static Logger logger = LoggerFactory.getLogger(ValidateAndSendMessageResource.class);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(MessageDto messageDto) {
        try {
            // Call SendMessageService to process the messageDto
            ValidateSendMessageService sendMessageService = new ValidateSendMessageService();
            logger.info("Message received :" +messageDto.toString());
            
            setMessageState(messageDto);            
            
            MessageDto processedMessageDto = sendMessageService.sendMessage(messageDto);
            
            return Response.status(Status.OK).entity(processedMessageDto).build();
            
        } catch (InvalidMessageException e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
            
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
    
    private void setMessageState(MessageDto messageDto) {
        if(!StringUtil.isNullOrEmpty(messageDto.getTemplateId())) {
        	messageDto.setTemplateId(messageDto.getTemplateId().toLowerCase());
        }

        if(!StringUtil.isNullOrEmpty(messageDto.getTemplateOwner())) {
        	messageDto.setTemplateOwner(messageDto.getTemplateOwner().toUpperCase());
        }

        if(!StringUtil.isNullOrEmpty(messageDto.getMessageType())) {
        	messageDto.setMessageType(messageDto.getMessageType().toUpperCase());
        }
    }
}