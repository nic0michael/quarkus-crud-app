package za.co.nico.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import za.co.nico.entities.CmEnrichedDisplayData;

@ApplicationScoped
public class CmEnrichedDisplayDataRepo implements PanacheRepository<CmEnrichedDisplayData>{

    public CmEnrichedDisplayData findByCmTemplateName(String cmTemplateName) {
        return find("cmTemplateName", cmTemplateName).firstResult();
    }

}
