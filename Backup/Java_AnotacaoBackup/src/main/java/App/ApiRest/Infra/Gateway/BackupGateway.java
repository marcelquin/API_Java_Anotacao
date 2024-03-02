package App.ApiRest.Infra.Gateway;

import App.ApiRest.Domain.Backup;
import App.ApiRest.Infra.Persistence.Entity.BackupEntity;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface BackupGateway {

    public ResponseEntity<List<BackupEntity>> ListarEventos();

    public ResponseEntity<Backup> NovoEvento(@RequestParam OpcaoBackup opcaoBackup,@RequestParam String nome,
                                             @RequestParam String descrsao, @RequestParam String codigo,
                                             @RequestParam Linguagem linguagem,@RequestParam LocalDateTime dataCriacao,
                                             @RequestParam List<String> arquivos);


}
