package za.co.nico.repos;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import za.co.nico.entities.CmTemplate;

@ApplicationScoped
public class CmTemplateRepo implements PanacheRepository<CmTemplate> {

    public CmTemplate findByCmTemplateName(String cmTemplateName) {
        return find("cmTemplateName", cmTemplateName).firstResult();
    }


    public List<CmTemplate> getCmTemplatesByCmTemplateOwnerNames() {
        return list("SELECT c FROM CmTemplate c ORDER BY c.cmTemplateOwnerName");
    }
    

    public List<CmTemplate> getCmTemplatesByCmTemplateOwnerName(String cmTemplateOwnerName) {
        return list("SELECT c FROM CmTemplate c WHERE c.cmTemplateOwnerName = ?1 ORDER BY c.cmTemplateName", cmTemplateOwnerName);
    }
    
}
