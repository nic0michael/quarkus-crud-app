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
import java.util.UUID;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import za.co.nico.dtos.MessageDto;
import za.co.nico.validators.MessageDtoValidator;

@ApplicationScoped
public class ValidateSendMessageService {

	private static final Logger logger = LoggerFactory.getLogger(ValidateSendMessageService.class);

	private static final String REST_SERVICE_URL = "http://localhost:8080/message-api/sendmessage";
	private final boolean TEST = false;
	private final boolean RUN = true;

	public MessageDto sendMessage(MessageDto messageDto) throws Exception {
		boolean operation = TEST; 
		messageDto.setMessageId(makeMessageId());
		if (!MessageDtoValidator.validateSendBody(messageDto)) {
			Client client = null;
			
			if (operation) {
				try {
					client = ClientBuilder.newClient();

					WebTarget target = client.target(REST_SERVICE_URL);

					MessageDto responseDto = target.request(MediaType.APPLICATION_JSON)
							.post(Entity.entity(messageDto, MediaType.APPLICATION_JSON), MessageDto.class);

					client.close();
				} catch (Exception e) {
					logger.error("Failed to send message ", e);
				} finally {
					client.close();
				}
			}

		} else {
			logger.error("Message failed Validation");
		}

		logger.info("messageDto : " + messageDto);

		return messageDto;
	}

	private String makeMessageId() {		

        LocalDate currentDate = LocalDate.now();
        String dateString = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        LocalDateTime currentDateTime = LocalDateTime.now();
        String timestampString = currentDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        
        String messageId = timestampString + "-" + UUID.randomUUID().toString();
        
		return messageId;
	}
}
