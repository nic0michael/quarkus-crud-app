package za.co.nico.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import za.co.nico.entities.CmTemplate;

@ApplicationScoped
public class CmTemplateRepo implements PanacheRepository<CmTemplate> {

    public CmTemplate findByCmTemplateName(String cmTemplateName) {
        return find("cmTemplateName", cmTemplateName).firstResult();
    }

}
