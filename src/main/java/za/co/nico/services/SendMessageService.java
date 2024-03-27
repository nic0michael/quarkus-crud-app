package za.co.nico.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

import za.co.nico.dtos.MessageDto;
import za.co.nico.validators.MessageDtoValidator;

@ApplicationScoped
public class SendMessageService {

	private static final Logger logger = LoggerFactory.getLogger(SendMessageService.class);

	private static final String REST_SERVICE_URL = "http://localhost:8080/message-api/sendmessage";

	public MessageDto sendMessage(MessageDto messageDto) throws Exception {
		if (!MessageDtoValidator.validateSendBody(messageDto)) {
			Client client = null;
			try {
				client = ClientBuilder.newClient();

				WebTarget target = client.target(REST_SERVICE_URL);

				MessageDto responseDto = target.request(MediaType.APPLICATION_JSON)
						.post(Entity.entity(messageDto, MediaType.APPLICATION_JSON), MessageDto.class);

				client.close();
			} catch (Exception e) {
				logger.error("Failed to send message ",e);
			} finally {
				client.close();
			}
			
		} else {
			logger.error("Message failed Validation");
		}
		
		logger.info("messageDto : "+messageDto);

		return messageDto;
	}

}
