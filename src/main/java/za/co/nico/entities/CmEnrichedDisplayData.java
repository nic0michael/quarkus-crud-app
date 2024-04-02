package za.co.nico.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 *  show create table Cm_Enriched_Display_Data;
 */
@Entity
@Table(name = "Cm_Enriched_Display_Data", schema = "sakila")
public class CmEnrichedDisplayData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
	private Long id;
	
	private LocalDateTime creationDate;
	
    @NotNull
    @Column(name = "Cm_Changed_By", nullable = false)
    private String changedBy;
		
	@Column(name = "Cm_Template_Name", nullable = false, unique = true)
	private String cmTemplateName; // MICA_BlackFriday2024

	@NotNull
    @Column(name = "Cm_Template_Content", nullable = false)
    private String cmTemplateContent;  	

	@Column(name = "Cm_Data_Content", nullable = false)
	private String cmDataContent;
	
	@Column(name = "Cm_Enriched_Data_Content_Content", nullable = false)
	private String cmEnrichedDisplayDataContent;
	
	public CmEnrichedDisplayData() {
		super();
	}

	

	public CmEnrichedDisplayData(Long id, LocalDateTime creationDate, @NotNull String changedBy, String cmTemplateName,
			@NotNull String cmTemplateContent, String cmDataContent, String cmEnrichedDisplayDataContent) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.changedBy = changedBy;
		this.cmTemplateName = cmTemplateName;
		this.cmTemplateContent = cmTemplateContent;
		this.cmDataContent = cmDataContent;
		this.cmEnrichedDisplayDataContent = cmEnrichedDisplayDataContent;
	}



	@Override
	public String toString() {
		return "CmEnrichedDisplayData [id=" + id + ", creationDate=" + creationDate + ", changedBy=" + changedBy
				+ ", cmTemplateName=" + cmTemplateName + ", cmTemplateContent=" + cmTemplateContent + ", cmDataContent="
				+ cmDataContent + ", cmEnrichedDisplayDataContent=" + cmEnrichedDisplayDataContent + "]";
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public LocalDateTime getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}



	public String getChangedBy() {
		return changedBy;
	}



	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
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
