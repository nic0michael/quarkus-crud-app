package za.co.nico.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import za.co.nico.entities.CmSampleData;

@ApplicationScoped
public class CmSampleDataRepo implements PanacheRepository<CmSampleData> {

    public CmSampleData findByCmTemplateName(String cmTemplateName) {
        return find("cmTemplateName", cmTemplateName).firstResult();
    }

}
