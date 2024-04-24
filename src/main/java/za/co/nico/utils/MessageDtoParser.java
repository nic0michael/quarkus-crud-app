package za.co.nico.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.nico.dtos.MessageDto;

public class MessageDtoParser {


	private static Logger logger = LoggerFactory.getLogger(MessageDtoParser.class);

	private static ObjectMapper objectMapper = new ObjectMapper();
	


	/**
	 * Deserialize JSON content from given JSON content String.
	 *
	 * @param jsonString
	 * @return
	 * @throws JsonProcessingException
	 * 
	 *  We don't log body as enriched emails have large amount of HTML content
	 *  	
	 */
	public static MessageDto jsonParse(String jsonString) throws JsonProcessingException {
//		logger.info("jsonString : \n->" + jsonString +"<-");
		MessageDto messageDto = objectMapper.readValue(jsonString, MessageDto.class);
		return messageDto;
	}

	/**
	 * Serialize any Java value to a String.
	 *
	 * @param messageDto
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String jsonSerializeMessageDto(MessageDto messageDto) throws JsonProcessingException {
		logger.info("MessageDto received");
		String jsonString = objectMapper.writeValueAsString(messageDto);
		return jsonString;
	}

}
