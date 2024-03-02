package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Backup;
import App.ApiRest.Infra.Gateway.BackupGateway;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public class UseCaseBackupPost {
    private final BackupGateway backupGateway;

    public UseCaseBackupPost(BackupGateway backupGateway) {
        this.backupGateway = backupGateway;
    }

    public ResponseEntity<Backup> NovoEvento(@RequestParam OpcaoBackup opcaoBackup, @RequestParam String nome,
                                             @RequestParam String descrsao, @RequestParam String codigo,
                                             @RequestParam Linguagem linguagem, @RequestParam LocalDateTime dataCriacao,
                                             @RequestParam List<String> arquivos)
    { return backupGateway.NovoEvento(opcaoBackup, nome, descrsao, codigo, linguagem, dataCriacao, arquivos);}
}
