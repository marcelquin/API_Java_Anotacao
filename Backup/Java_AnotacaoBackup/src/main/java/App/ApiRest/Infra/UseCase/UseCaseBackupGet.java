package App.ApiRest.Infra.UseCase;

import App.ApiRest.Infra.Gateway.BackupGateway;
import App.ApiRest.Infra.Persistence.Entity.BackupEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UseCaseBackupGet {
    private final BackupGateway backupGateway;

    public UseCaseBackupGet(BackupGateway backupGateway) {
        this.backupGateway = backupGateway;
    }

    public ResponseEntity<List<BackupEntity>> ListarEventos()
    { return backupGateway.ListarEventos();}
}
