package za.co.nico.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import za.co.nico.entities.CmSampleData;
import za.co.nico.exceptions.DatabaseException;
import za.co.nico.repos.CmSampleDataRepo;

@ApplicationScoped
public class CmSampleDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(CmSampleDataService.class);


	@Inject
	CmSampleDataRepo cmSampleDataRepo;
	

	public CmSampleData findById(Long id ) throws Exception {
		return cmSampleDataRepo.findById(id);
	}

	public CmSampleData findByCmTemplateName(String cmTemplateName ) throws Exception {
		return cmSampleDataRepo.findByCmTemplateName(cmTemplateName);
	}
	
	public List<CmSampleData> listAll() throws Exception {
		 return cmSampleDataRepo.listAll();
	}
		
	@Transactional
	public CmSampleData createCmSampleData(CmSampleData cmSampleData) throws Exception {
		Long id = null;
        cmSampleDataRepo.persist(cmSampleData);
        id = cmSampleData.getId();
        logger.info("Created CmSampleData with ID: ",id);
        return cmSampleData;
    }
	
	@Transactional
	public CmSampleData updateCmSampleData(CmSampleData cmSampleData) throws Exception {
		Long id = cmSampleData.getId();
		CmSampleData foundCmSampleData = findById( id );
		if(null == foundCmSampleData) {
			logger.error("Could not find sample data : id : " + id);
			throw new DatabaseException("Could not find sample data : id : " + id);
		}
		foundCmSampleData.setCmSampleData(cmSampleData);
		cmSampleDataRepo.persist(foundCmSampleData);
		return foundCmSampleData;
	}	

	@Transactional
	public void deleteCmSampleData(CmSampleData cmSampleData) throws Exception {
		String cmTemplateName = cmSampleData.getCmTemplateName();
		deleteCmSampleDataByCmTemplateName(cmTemplateName);
	}	

	@Transactional
	public void deleteCmSampleDataByCmTemplateName(String cmTemplateName) throws Exception {
		CmSampleData foundCmSampleData = findByCmTemplateName( cmTemplateName );
		if(null == foundCmSampleData) {
			logger.error("Could not find sample data : cmTemplateName : " + cmTemplateName);
			throw new DatabaseException("Could not find sample data : cmTemplateName : " + cmTemplateName);
		}
		cmSampleDataRepo.delete(foundCmSampleData);
	}
	
	@Transactional
	public void deleteCmSampleDataById(Long id) throws Exception {
		CmSampleData foundCmSampleData = findById(id);
		if(null == foundCmSampleData) {
			logger.error("Could not find sample data : id : " + id);
			throw new DatabaseException("Could not find sample data : id : " + id);
		}
		cmSampleDataRepo.delete(foundCmSampleData);
	}

	@Transactional
	public List<CmSampleData> findAllByCmTemplateOwnerName(String cmTemplateOwnerName) throws Exception {
		logger.info("Called findAllByCmTemplateOwnerName : cmTemplateOwnerName : "+cmTemplateOwnerName);
		List<CmSampleData> dataList = new ArrayList<CmSampleData>();
		List<CmSampleData> foundList = cmSampleDataRepo.findAllByCmTemplateOwnerName(cmTemplateOwnerName);
		
		if(null != foundList && !foundList.isEmpty()) {
			logger.info("foundList size : "+foundList.size());
			dataList.addAll(foundList);
		}
		return dataList;
	}

}
