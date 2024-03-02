package App.ApiRest.Infra.Persistence.Repository;

import App.ApiRest.Infra.Persistence.Entity.PacoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacoteRepository extends JpaRepository<PacoteEntity,Long> {

        boolean existsBynome(String nome);
        boolean existsBycodigo(String codigo);

        Optional<PacoteEntity> findBynome(String nome);
        Optional<PacoteEntity> findBycodigo(String codigo);
}
