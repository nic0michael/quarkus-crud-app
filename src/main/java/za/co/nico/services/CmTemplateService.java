package za.co.nico.services;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import za.co.nico.entities.CmTemplate;
import za.co.nico.exceptions.DatabaseException;
import za.co.nico.repos.CmTemplateRepo;

@ApplicationScoped
public class CmTemplateService {
	
	private static final Logger logger = LoggerFactory.getLogger(CmTemplateService.class);
	
	@Inject
	CmTemplateRepo templateRepo;
	
	public CmTemplate findById(Long id ) throws Exception {
		return templateRepo.findById(id);
	}

	public CmTemplate findByCmTemplateName(String cmTemplateName ) throws Exception {
		return templateRepo.findByCmTemplateName(cmTemplateName);
	}
	
	public List<CmTemplate> listAll() throws Exception {
		 return templateRepo.listAll();
	}
	

	
	public List<CmTemplate> listAllByCmTemplateCategory(String cmTemplateCategory)  throws Exception {
        return templateRepo.list("cmTemplateCategory", cmTemplateCategory);
    }
	

    public List<CmTemplate> listAllByCmTemplateOwnerName(String cmTemplateOwnerName)  throws Exception {
        return templateRepo.list("cmTemplateOwnerName", cmTemplateOwnerName);
    }
    
	
	public CmTemplate createCmTemplate(CmTemplate cmTemplate) throws Exception {
		Long iD = null;
        templateRepo.persist(cmTemplate);
        iD = cmTemplate.getId();
        logger.info("Created CmTemplate with ID: ", iD);
        return cmTemplate;
    }
	
	
	public CmTemplate updateCmTemplate(CmTemplate cmTemplate) throws Exception {
		String cmTemplateName = cmTemplate.getCmTemplateName();
		CmTemplate foundCmTemplate = findByCmTemplateName( cmTemplateName );
		if(null == foundCmTemplate) {
			logger.error("Could not find template : cmTemplateName : " + cmTemplateName);
			throw new DatabaseException("Could not find template : cmTemplateName : " + cmTemplateName);
		}
		foundCmTemplate.setCmTemplate(cmTemplate);
		templateRepo.persist(foundCmTemplate);
		return foundCmTemplate;
	}
	
	
	public void deleteCmTemplate(CmTemplate cmTemplate) throws Exception {
		String cmTemplateName = cmTemplate.getCmTemplateName();
		deleteCmTemplateByCmTemplateName(cmTemplateName);
	}
	

	public void deleteCmTemplateByCmTemplateName(String cmTemplateName) throws Exception {
		CmTemplate foundCmTemplate = findByCmTemplateName( cmTemplateName );
		if(null == foundCmTemplate) {
			logger.error("Could not find template : cmTemplateName : " + cmTemplateName);
			throw new DatabaseException("Could not find template : cmTemplateName : " + cmTemplateName);
		}
		templateRepo.delete(foundCmTemplate);
	}

	public void deleteCmTemplateById(Long id) throws Exception {
		CmTemplate foundCmTemplate = findById(id);
		if(null == foundCmTemplate) {
			logger.error("Could not find template : id : " + id);
			throw new DatabaseException("Could not find template : id : " + id);
		}
		templateRepo.delete(foundCmTemplate);
	}

}
