package za.co.nico.entities;

import java.time.LocalDateTime;

import io.quarkus.runtime.util.StringUtil;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import za.co.nico.exceptions.InvalidTemplateException;

/**
 *  show create table CM_Template;
 */
@Entity
@Table(name = "CM_Template", schema = "sakila")
public class CmTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    private Long id;	


    @Column(name = "Last_Updated") // Custom column name for creationDate
    private LocalDateTime creationDate;
    
    @NotNull
    @Column(name = "Cm_Template_Name", nullable = false, unique = true) // Ensure uniqueness at the database level
    private String cmTemplateName;  
    
    @NotNull
    @Column(name = "Cm_Template_Category", nullable = false)
    private String cmTemplateCategory;  
    
    @NotNull
    @Column(name = "Cm_Template_Content", nullable = false)
    private String cmTemplateContent; 
        
    @NotNull
    @Column(name = "Cm_Campaign_Name", nullable = false)
    private String cmCampaignName;  
    
    @NotNull
    @Column(name = "Cm_Template_Owner_Name", nullable = false)
    private String cmTemplateOwnerName;  
    

    @NotNull
    @Column(name = "Cm_Changed_By", nullable = false)
    private String changedBy;

     

	public CmTemplate() {
		super();
	}

	public CmTemplate(Long id, LocalDateTime creationDate, @NotNull String changedBy, String cmTemplateName,
			String cmTemplateCategory, String cmTemplateContent, String cmCampaignName, String cmTemplateOwnerName) {
		super();
		
		this.id = id;
		this.creationDate = creationDate;
		this.changedBy = changedBy;
		this.cmTemplateName = cmTemplateName;
		this.cmTemplateCategory = cmTemplateCategory;
		this.cmTemplateContent = cmTemplateContent;
		this.cmCampaignName = cmCampaignName;
		this.cmTemplateOwnerName = cmTemplateOwnerName;
	}


	public CmTemplate(LocalDateTime creationDate, @NotNull String changedBy, String cmTemplateName,
			String cmTemplateCategory, String cmTemplateContent, String cmCampaignName, String cmTemplateOwnerName) {
		super();
		
		this.creationDate = creationDate;
		this.changedBy = changedBy;
		this.cmTemplateName = cmTemplateName;
		this.cmTemplateCategory = cmTemplateCategory;
		this.cmTemplateContent = cmTemplateContent;
		this.cmCampaignName = cmCampaignName;
		this.cmTemplateOwnerName = cmTemplateOwnerName;
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

	public String getCmTemplateCategory() {
		return cmTemplateCategory;
	}

	public void setCmTemplateCategory(String cmTemplateCategory) {
		this.cmTemplateCategory = cmTemplateCategory;
	}

	public String getCmTemplateContent() {
		return cmTemplateContent;
	}

	public void setCmTemplateContent(String cmTemplateContent) {
		this.cmTemplateContent = cmTemplateContent;
	}

	public String getCmCampaignName() {
		return cmCampaignName;
	}

	public void setCmCampaignName(String cmCampaignName) {
		this.cmCampaignName = cmCampaignName;
	}

	public String getCmTemplateOwnerName() {
		return cmTemplateOwnerName;
	}

	public void setCmTemplateOwnerName(String cmTemplateOwnerName) {
		this.cmTemplateOwnerName = cmTemplateOwnerName;
	}

	public void setCmTemplate(CmTemplate cmTemplate) throws Exception {
	    if (cmTemplate == null || cmTemplate.isEmpty()) {
	        throw new InvalidTemplateException("Template is empty or NULL");
	    }
	    
	    this.id = cmTemplate.getId();
	    this.creationDate = cmTemplate.getCreationDate();
	    this.changedBy = cmTemplate.getChangedBy();
	    this.cmTemplateName = cmTemplate.getCmTemplateName();
	    this.cmTemplateCategory = cmTemplate.getCmTemplateCategory();
	    this.cmTemplateContent = cmTemplate.getCmTemplateContent();
	    this.cmCampaignName = cmTemplate.getCmCampaignName();
	    this.cmTemplateOwnerName = cmTemplate.getCmTemplateOwnerName();
	}

	private boolean isEmpty() {
		boolean isEmpty = false;
		if(StringUtil.isNullOrEmpty(cmTemplateName)) isEmpty = true;
		if(StringUtil.isNullOrEmpty(cmTemplateCategory)) isEmpty = true;
		if(StringUtil.isNullOrEmpty(cmTemplateOwnerName)) isEmpty = true;
		
		return isEmpty;
	}



}
