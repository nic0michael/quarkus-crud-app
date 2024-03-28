package za.co.nico.validators;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.quarkus.runtime.util.StringUtil;
import za.co.nico.dtos.MessageDto;
import za.co.nico.exceptions.InvalidMessageException;

public class MessageDtoValidator {
	private static Logger logger = LoggerFactory.getLogger(MessageDtoValidator.class);

	private static final String TEXT_CONTENT_TYPE = "text/plain";
	private static final String HTML_CONTENT_TYPE = "text/html";
	private static final String JSON_CONTENT_TYPE = "text/json";
	private static final String VALIDATION_PASSED = "Validation has passed";
	private static final String VALIDATION_FAILED = "Validation has failed";
	
	private MessageDtoValidator() {}

	public static String getMessageStatus(MessageDto messageDto) throws Exception {
		Map<String, String> validationErrors = new HashMap<String, String>();
		boolean hasFailed = validateSendBody( messageDto,validationErrors);
		return getMessageStatus( messageDto,validationErrors).toUpperCase();
	}

	public static boolean validateSendBody(MessageDto messageDto) throws Exception {
		boolean hasFailed = false;
		Map<String, String> validationErrors = new HashMap<String, String>();
		return validateSendBody(messageDto,validationErrors);
	}
	
	public static boolean validateSendBody(MessageDto messageDto,Map<String, String> validationErrors) throws Exception {
		boolean hasFailed = false;

		if (null == messageDto) {
			hasFailed = true;
			validationErrors.put("Error 400", "Fields needed not in body");
		} else {
			messageDto.setMessageStatus("");
			messageDto.setSendDate(null);
			lookForMissingPrimaryFields(messageDto,validationErrors);
			
			if (!StringUtil.isNullOrEmpty(messageDto.getMessageType())) {
				String messageType = messageDto.getMessageType();

				switch (messageType.toUpperCase()) {
				case "EMAIL":
					lookForMissingEmailFields(messageDto,validationErrors);
					break;
					
				case "SMS":
					lookForMissingSmsFields(messageDto,validationErrors);
					break;

				case "WHATSAPP":
					lookForMissingWhatsappFields(messageDto,validationErrors);
					break;	

				case "FACEBOOK":
					lookForMissingFacebookFields(messageDto,validationErrors);
					break;

				default:
					logger.error("Unknown message type: " + messageType);
					hasFailed = true;
					validationErrors.put("Error 405", "Unknown MessageType : " + messageType);
				}
			} else {
				
				validationErrors.put("Error 404", "MessageType not set");
			}

		}
		logger.info("validationErrors size: "+validationErrors.size());
		if( !validationErrors.isEmpty() ) {
			hasFailed = true;
			logger.info("Validation Failed");
		} 
		String messageStatus = getMessageStatus(messageDto,validationErrors);
		messageDto.setMessageStatus(messageStatus);
		logger.info("Validation Result failed : "+hasFailed);

		return hasFailed;
	}

	private static void lookForMissingPrimaryFields(MessageDto messageDto,Map<String, String> validationErrors) throws Exception{

		if (StringUtil.isNullOrEmpty(messageDto.getSenderSystem())) {
			validationErrors.put("Error 402", "SenderSystem not set");
			throw new InvalidMessageException("SenderSystem not set");
		}
		if (StringUtil.isNullOrEmpty(messageDto.getUserId())) {
			validationErrors.put("Error 403", "UserId not set");
			throw new InvalidMessageException("UserId not set");
		}
		if (StringUtil.isNullOrEmpty(messageDto.getMessageType())) {
			validationErrors.put("Error 404", "MessageType not set");
		}
	}

	private static void lookForMissingEmailFields(MessageDto messageDto,Map<String, String> validationErrors) {

		/**
		 * New business rule if null make it : "message-api@loyaltyplus.aero"
		 * this will be set in MessageSenderProcessor that calls this class
		 */
//		if (StringUtil.isNullOrEmpty(messageDto.getEmailFrom())) {
//			hasFailed = true;
//			validationErrors.put("Error 406", "EmailFrom not set");
//			messageDto.setEmailFrom("message-api@loyaltyplus.aero");
//		}
		

		if (StringUtil.isNullOrEmpty(messageDto.getEmailTo())) {
			validationErrors.put("Error 407", "EmailTo not set");
		}

		if (StringUtil.isNullOrEmpty(messageDto.getReplyEmail())) {
			validationErrors.put("Error 408", "ReplyEmail not set");
		}

		if (StringUtil.isNullOrEmpty(messageDto.getSubject())) {
			validationErrors.put("Error 409", "Subject not set");
		}

		if (StringUtil.isNullOrEmpty(messageDto.getEmailContentType())) {
			validationErrors.put("Error 410", "EmailContentType not set");
		}

		if (StringUtil.isNullOrEmpty(messageDto.getBody()) && StringUtil.isNullOrEmpty(messageDto.getEnrichedBody())) {
			validationErrors.put("Error 411", "Body or EnrichedBody not set");
		}

		if (!StringUtil.isNullOrEmpty(messageDto.getBody()) 
				&& StringUtil.isNullOrEmpty(messageDto.getEnrichedBody())
				&& StringUtil.isNullOrEmpty(messageDto.getTemplateId())) {
			validationErrors.put("Error 412", "Body set and TemplateId not set");
		}

		if (!StringUtil.isNullOrEmpty(messageDto.getBody()) 
				&& StringUtil.isNullOrEmpty(messageDto.getEnrichedBody())
				&& StringUtil.isNullOrEmpty(messageDto.getTemplateOwner())) {
			validationErrors.put("Error 413", "Body set and TemplateOwner not set");
		}
		

		if (!StringUtil.isNullOrEmpty(messageDto.getEmailContentType())) {
			String contentType = messageDto.getEmailContentType();
			if(! HTML_CONTENT_TYPE.equalsIgnoreCase(contentType) &&
			! TEXT_CONTENT_TYPE.equalsIgnoreCase(contentType) ){
				validationErrors.put("Error 414", "Invalid Content Type : "+contentType);
			}
			
		}
	}

	private static void lookForMissingSmsFields(MessageDto messageDto,Map<String, String> validationErrors) {

		if (StringUtil.isNullOrEmpty(messageDto.getBody()) && StringUtil.isNullOrEmpty(messageDto.getEnrichedBody())) {
			validationErrors.put("Error 415", "Body or EnrichedBody not set");
		}

		if (!StringUtil.isNullOrEmpty(messageDto.getBody()) && StringUtil.isNullOrEmpty(messageDto.getTemplateId())) {
			validationErrors.put("Error 416", "Body set and TemplateId not set");
		}
		
		if (StringUtil.isNullOrEmpty(messageDto.getCellNumber())) {
			validationErrors.put("Error 417", "CellNumber not set");
		}
	}
	
	private static void lookForMissingWhatsappFields(MessageDto messageDto,Map<String, String> validationErrors) {
		// TODO Auto-generated method stub
	}	

	private static void lookForMissingFacebookFields(MessageDto messageDto,Map<String, String> validationErrors) {
		// TODO Auto-generated method stub
	}

	private static String getMessageStatus(MessageDto messageDto,Map<String, String> validationErrors) {

		StringBuilder messageStatus = new StringBuilder();
		if (!validationErrors.isEmpty()) {
			messageStatus.append(VALIDATION_FAILED);

			for (Map.Entry<String, String> entry : validationErrors.entrySet()) {
				messageStatus.append(" | ");
				String key = entry.getKey();
				messageStatus.append(key);
				messageStatus.append(" : ");
				String value = entry.getValue();
				messageStatus.append(value);
			}
		} else {
			messageStatus.append(VALIDATION_PASSED);
		}

		logger.info("MessageStatus : "+messageStatus);
		return messageStatus.toString();
	}

}