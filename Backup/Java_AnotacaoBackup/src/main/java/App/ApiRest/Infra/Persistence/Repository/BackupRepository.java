package App.ApiRest.Infra.Persistence.Repository;

import App.ApiRest.Infra.Persistence.Entity.BackupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupRepository extends JpaRepository<BackupEntity, Long> {
}
