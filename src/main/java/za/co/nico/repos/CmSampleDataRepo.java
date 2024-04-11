package za.co.nico.repos;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import za.co.nico.entities.CmSampleData;

@ApplicationScoped
public class CmSampleDataRepo implements PanacheRepository<CmSampleData> {

    public CmSampleData findByCmTemplateName(String cmTemplateName) {
        return find("cmTemplateName", cmTemplateName).firstResult();
    }
    

    public List<CmSampleData> findAllByOwnerName(String ownerName) {
        return find("SELECT b FROM CmSampleData b JOIN CmTemplate a ON a.cmTemplateName = b.cmTemplateName WHERE a.cmTemplateOwnerName = ?1", ownerName).list();
    }

}
