package za.co.nico.entities;

import java.time.LocalDateTime;
import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

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
import za.co.nico.exceptions.InvalidSampleDataException;


/**
 *  show create table CM_SampleData;
 */
@Entity
@Table(name = "CM_Sample_Data", schema = "sakila")
public class CmSampleData {


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
	
	@Column(name = "Cm_Map_Payload")
	private String cmMapPayloadJson;
	
	public CmSampleData() {
		super();
	}
	
	public CmSampleData(Long id, LocalDateTime creationDate, @NotNull String changedBy, String cmTemplateName,
			String cmMapPayloadJson) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.changedBy = changedBy;
		this.cmTemplateName = cmTemplateName;
		this.cmMapPayloadJson = cmMapPayloadJson;
	}
	
	
	
	@Override
	public String toString() {
		return "CmSampleData [id=" + id + ", creationDate=" + creationDate + ", changedBy=" + changedBy
				+ ", cmTemplateName=" + cmTemplateName + ", cmMapPayloadJson=" + cmMapPayloadJson + "]";
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

	public String getCmMapPayloadJson() {
		return cmMapPayloadJson;
	}


	public void setCmMapPayloadJson(String cmMapPayloadJson) {
		this.cmMapPayloadJson = cmMapPayloadJson;
	}


	public void setCmSampleData(CmSampleData cmSampleData) throws Exception {
		if (cmSampleData == null || cmSampleData.isEmpty()) {
	        throw new InvalidSampleDataException("Template is empty or NULL");
	    }
		
		id = cmSampleData.getId();
		creationDate  = cmSampleData.getCreationDate();
		changedBy = cmSampleData.getChangedBy();
		cmTemplateName = cmSampleData.getCmTemplateName();
		cmMapPayloadJson = cmSampleData.getCmMapPayloadJson();
	}
	

	private boolean isEmpty() {
		boolean isEmpty = false;
		if(StringUtil.isNullOrEmpty(cmTemplateName)) isEmpty = true;
		if(StringUtil.isNullOrEmpty(cmMapPayloadJson)) isEmpty = true;
		
		return isEmpty;
	}

}
