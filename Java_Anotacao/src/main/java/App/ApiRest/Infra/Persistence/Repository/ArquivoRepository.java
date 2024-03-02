package App.ApiRest.Infra.Persistence.Repository;

import App.ApiRest.Infra.Persistence.Entity.ArquivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoEntity,Long> {
}
