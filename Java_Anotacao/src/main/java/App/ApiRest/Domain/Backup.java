package App.ApiRest.Domain;

import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;

import java.time.LocalDateTime;
import java.util.List;

public record Backup(OpcaoBackup opcaoBackup, String nome, String descrsao, String codigo,
                     Linguagem linguagem, LocalDateTime dataCriacao,
                     List<String> arquivos) {
}
