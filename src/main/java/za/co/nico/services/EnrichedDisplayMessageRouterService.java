package za.co.nico.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import za.co.nico.dtos.MessageDto;

@ApplicationScoped
public class EnrichedDisplayMessageRouterService {
	
	private static final Logger logger = LoggerFactory.getLogger(EnrichedDisplayMessageRouterService.class);

	/**
	 * This Camel Router should enrich the MessageBody in MessageDto
	 * 
	 * we have a dummy operation until this is coded
	 */
	public void enrichMessageBody(MessageDto messageDto) {
		logger.info("enrichMessageBody called");
		
		messageDto.setEnrichedBody(messageDto.getBody());
		logger.info("messageDto : "+messageDto);
	}

}
