package za.co.nico.dtos;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MessageDto {

	private Long id;
	private String messageId;
	private String senderSystem;
	private String templateOwner;
	private String userId;
	private String messageType;
	private String templateId;
	private String templateName;
	private String salutation;
	private String valediction;
    private Map<String, String> mapPayload; // Object>
	private String messageTo;
	private String emailFrom;
	private String emailTo;
	private String emailCc;
	private String emailBcc;
	private String replyEmail;
	private String subject;
	private String emailContentType;
	private String cellNumber;
	private String body;
	private List<String> attachments;
	private String enrichedBody;
	private Integer priority; // zero is highest
	private String sendDate;
	private String messageStatus; //invalid (missing fields), failed, sent, delivered, and opened


	public void setDateNow() {
		setSendDate(LocalDateTime.now().toString());
	}	
	

	public MessageDto() {
		super();
	}


	public MessageDto(Long id, String messageId, String senderSystem, String templateOwner, String userId,
			String messageType, String templateId, String templateName, String salutation, String valediction,
			Map<String, String> mapPayload, String messageTo, String emailFrom,
			String emailTo, String emailCc, String emailBcc, String replyEmail, String subject, String emailContentType,
			String cellNumber, String body, List<String> attachments, String enrichedBody, Integer priority,
			String sendDate, String messageStatus) {
		super();
		this.id = id;
		this.messageId = messageId;
		this.senderSystem = senderSystem;
		this.templateOwner = templateOwner;
		this.userId = userId;
		this.messageType = messageType;
		this.templateId = templateId;
		this.templateName = templateName;
		this.salutation = salutation;
		this.valediction = valediction;
		this.mapPayload = mapPayload;
		this.messageTo = messageTo;
		this.emailFrom = emailFrom;
		this.emailTo = emailTo;
		this.emailCc = emailCc;
		this.emailBcc = emailBcc;
		this.replyEmail = replyEmail;
		this.subject = subject;
		this.emailContentType = emailContentType;
		this.cellNumber = cellNumber;
		this.body = body;
		this.attachments = attachments;
		this.enrichedBody = enrichedBody;
		this.priority = priority;
		this.sendDate = sendDate;
		this.messageStatus = messageStatus;
	}

	

	@Override
	public String toString() {
		return "MessageDto [id=" + id + ", messageId=" + messageId + ", senderSystem=" + senderSystem
				+ ", templateOwner=" + templateOwner + ", userId=" + userId + ", messageType=" + messageType
				+ ", templateId=" + templateId + ", templateName=" + templateName + ", salutation=" + salutation
				+ ", valediction=" + valediction + ", mapPayload=" + mapPayload + ", messageTo=" + messageTo
				+ ", emailFrom=" + emailFrom + ", emailTo=" + emailTo + ", emailCc=" + emailCc + ", emailBcc="
				+ emailBcc + ", replyEmail=" + replyEmail + ", subject=" + subject + ", emailContentType="
				+ emailContentType + ", cellNumber=" + cellNumber + ", body=" + body + ", attachments=" + attachments
				+ ", enrichedBody=" + enrichedBody + ", priority=" + priority + ", sendDate=" + sendDate
				+ ", messageStatus=" + messageStatus + "]";
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMessageId() {
		return messageId;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	public String getSenderSystem() {
		return senderSystem;
	}


	public void setSenderSystem(String senderSystem) {
		this.senderSystem = senderSystem;
	}


	public String getTemplateOwner() {
		return templateOwner;
	}


	public void setTemplateOwner(String templateOwner) {
		this.templateOwner = templateOwner;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getMessageType() {
		return messageType;
	}


	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}


	public String getTemplateId() {
		return templateId;
	}


	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


	public String getTemplateName() {
		return templateName;
	}


	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public String getSalutation() {
		return salutation;
	}


	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}


	public String getValediction() {
		return valediction;
	}


	public void setValediction(String valediction) {
		this.valediction = valediction;
	}


	public Map<String, String> getMapPayload() {
		return mapPayload;
	}


	public void setMapPayload(Map<String, String> mapPayload) {
		this.mapPayload = mapPayload;
	}

	public String getMessageTo() {
		return messageTo;
	}


	public void setMessageTo(String messageTo) {
		this.messageTo = messageTo;
	}


	public String getEmailFrom() {
		return emailFrom;
	}


	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}


	public String getEmailTo() {
		return emailTo;
	}


	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}


	public String getEmailCc() {
		return emailCc;
	}


	public void setEmailCc(String emailCc) {
		this.emailCc = emailCc;
	}


	public String getEmailBcc() {
		return emailBcc;
	}


	public void setEmailBcc(String emailBcc) {
		this.emailBcc = emailBcc;
	}


	public String getReplyEmail() {
		return replyEmail;
	}


	public void setReplyEmail(String replyEmail) {
		this.replyEmail = replyEmail;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getEmailContentType() {
		return emailContentType;
	}


	public void setEmailContentType(String emailContentType) {
		this.emailContentType = emailContentType;
	}


	public String getCellNumber() {
		return cellNumber;
	}


	public void setCellNumber(String cellNumber) {
		this.cellNumber = cellNumber;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public List<String> getAttachments() {
		return attachments;
	}


	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}


	public String getEnrichedBody() {
		return enrichedBody;
	}


	public void setEnrichedBody(String enrichedBody) {
		this.enrichedBody = enrichedBody;
	}


	public Integer getPriority() {
		return priority;
	}


	public void setPriority(Integer priority) {
		this.priority = priority;
	}


	public String getSendDate() {
		return sendDate;
	}


	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}


	public String getMessageStatus() {
		return messageStatus;
	}


	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}


	
	
}
