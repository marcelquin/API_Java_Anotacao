package App.ApiRest.Bussness;

import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "backup-service", url = "http://localhost:8090/Backup")
public interface BackupService {

    @PostMapping("/NovoEvento")
    public void NovoEvento(@RequestParam OpcaoBackup opcaoBackup, @RequestParam String nome,
                           @RequestParam String descrsao, @RequestParam String codigo,
                           @RequestParam Linguagem linguagem, @RequestParam LocalDateTime dataCriacao,
                           @RequestParam List<String> arquivos);
}
