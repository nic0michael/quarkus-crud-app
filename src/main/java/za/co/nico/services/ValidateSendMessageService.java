package za.co.nico.services;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import za.co.nico.dtos.MessageDto;
import za.co.nico.utils.MessageDtoParser;
import za.co.nico.validators.MessageDtoValidator;

@ApplicationScoped
public class ValidateSendMessageService {

	private static final Logger logger = LoggerFactory.getLogger(ValidateSendMessageService.class);

	private static final String REST_SERVICE_URL = "http://localhost:8080/message-api/sendmessage";
	private final boolean TEST = false;
	private final boolean RUN = true;

	public MessageDto sendMessage(MessageDto messageDto) throws Exception {
		boolean operation = TEST; 
		logger.info("sendMessage called : operation :"+operation);
		messageDto.setMessageId(makeMessageId());
		if (!MessageDtoValidator.validateSendBody(messageDto)) {			
			if (operation) {
				String messageRequest = MessageDtoParser.jsonSerializeMessageDto(messageDto);
				logger.info("sendMessage creating RestClient");
				try {
                    URL url = new URL(REST_SERVICE_URL);
					
					HttpURLConnection connection = null;

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setDoOutput(true);

                    OutputStream os = connection.getOutputStream();
                    os.write(messageRequest.getBytes()); 
                    os.flush();

                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // Process successful response
                        logger.info("Message sent successfully");
                    } else {
                        // Handle error response
                        logger.error("Failed to send message. Response code: " + connection.getResponseCode());
                    }


					
				} catch (Exception e) {
					logger.error("Failed to send message ", e);
				} finally {

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
