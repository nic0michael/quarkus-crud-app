package za.co.nico.dtos;

public class EnrichedDisplayDataDto {
	private String cmTemplateName;
	private String cmTemplateContent; 
	private String cmDataContent;
	private String cmEnrichedDisplayDataContent;
	
	public EnrichedDisplayDataDto() {
		super();
	}

	public EnrichedDisplayDataDto(String cmTemplateName, String cmTemplateContent, String cmDataContent,
			String cmEnrichedDisplayDataContent) {
		super();
		this.cmTemplateName = cmTemplateName;
		this.cmTemplateContent = cmTemplateContent;
		this.cmDataContent = cmDataContent;
		this.cmEnrichedDisplayDataContent = cmEnrichedDisplayDataContent;
	}

	public String getCmTemplateName() {
		return cmTemplateName;
	}

	public void setCmTemplateName(String cmTemplateName) {
		this.cmTemplateName = cmTemplateName;
	}

	public String getCmTemplateContent() {
		return cmTemplateContent;
	}

	public void setCmTemplateContent(String cmTemplateContent) {
		this.cmTemplateContent = cmTemplateContent;
	}

	public String getCmDataContent() {
		return cmDataContent;
	}

	public void setCmDataContent(String cmDataContent) {
		this.cmDataContent = cmDataContent;
	}

	public String getCmEnrichedDisplayDataContent() {
		return cmEnrichedDisplayDataContent;
	}

	public void setCmEnrichedDisplayDataContent(String cmEnrichedDisplayDataContent) {
		this.cmEnrichedDisplayDataContent = cmEnrichedDisplayDataContent;
	}
	
	

}
