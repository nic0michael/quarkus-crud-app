package za.co.nico.services;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
	
	public List<String> listCmTemplateNames(String cmTemplateOwnerName)  throws Exception {
		List<String> listCmTemplateNames = new ArrayList<String>();
		List<CmTemplate> templates = listAllByCmTemplateOwnerName(cmTemplateOwnerName);
		for (CmTemplate cmTemplate : templates) {
			listCmTemplateNames.add(cmTemplate.getCmTemplateName());
		}
	
		return listCmTemplateNames;
	}
	

	public List<String> cmTemplateOwnerNames()  throws Exception {
		List<String> cmTemplateOwnerNames = new ArrayList<String>();
		List<CmTemplate> templates = cmTemplateOwnerName();
		if (templates != null || !templates.isEmpty()) {
			for (CmTemplate cmTemplate : templates) {
				cmTemplateOwnerNames.add(cmTemplate.getCmTemplateOwnerName());
			}
		}
		
		return cmTemplateOwnerNames;
	}
	
	
	public List<CmTemplate> listAll() throws Exception {
		 return templateRepo.listAll();
	}
	

	
	public List<CmTemplate> listAllByCmTemplateCategory(String cmTemplateCategory)  throws Exception {
        return templateRepo.list("cmTemplateCategory", cmTemplateCategory);
    }
	

//    public List<CmTemplate> listAllByCmTemplateOwnerName(String cmTemplateOwnerName)  throws Exception {
//        return templateRepo.list("cmTemplateOwnerName", cmTemplateOwnerName);
//    }   

    public List<CmTemplate> listAllByCmTemplateOwnerName(String cmTemplateOwnerName)  throws Exception {
        return templateRepo.getCmTemplatesByCmTemplateOwnerName(cmTemplateOwnerName);
    }   

	
    public List<CmTemplate> cmTemplateOwnerName()  throws Exception {
        List<CmTemplate> templates = templateRepo.getCmTemplatesByCmTemplateOwnerNames();
        return templates;
    } 

	@Transactional
	public CmTemplate createCmTemplate(CmTemplate cmTemplate) throws Exception {
		Long iD = null;
        templateRepo.persist(cmTemplate);
        iD = cmTemplate.getId();
        logger.info("Created CmTemplate with ID: ", iD);
        return cmTemplate;
    }	

	@Transactional
	public CmTemplate updateCmTemplate(CmTemplate cmTemplate) throws Exception {
		Long id = cmTemplate.getId();
		CmTemplate foundCmTemplate = findById( id);
		if(null == foundCmTemplate) {
			logger.error("Could not find template : id : " + id);
			throw new DatabaseException("Could not find template : id : " + id);
		}
		foundCmTemplate.setCmTemplate(cmTemplate);
		templateRepo.persist(foundCmTemplate);
		return foundCmTemplate;
	}
	
	@Transactional
	public void deleteCmTemplate(CmTemplate cmTemplate) throws Exception {
		String cmTemplateName = cmTemplate.getCmTemplateName();
		deleteCmTemplateByCmTemplateName(cmTemplateName);
	}

	@Transactional
	public void deleteCmTemplateByCmTemplateName(String cmTemplateName) throws Exception {
		CmTemplate foundCmTemplate = findByCmTemplateName( cmTemplateName );
		if(null == foundCmTemplate) {
			logger.error("Could not find template : cmTemplateName : " + cmTemplateName);
			throw new DatabaseException("Could not find template : cmTemplateName : " + cmTemplateName);
		}
		templateRepo.delete(foundCmTemplate);
	}

	@Transactional
	public void deleteCmTemplateById(Long id) throws Exception {
		CmTemplate foundCmTemplate = findById(id);
		if(null == foundCmTemplate) {
			logger.error("Could not find template : id : " + id);
			throw new DatabaseException("Could not find template : id : " + id);
		}
		templateRepo.delete(foundCmTemplate);
	}

}
