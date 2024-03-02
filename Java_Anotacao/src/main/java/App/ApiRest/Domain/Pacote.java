package App.ApiRest.Domain;

import App.ApiRest.Infra.Persistence.Enum.Linguagem;

import java.time.LocalDateTime;
import java.util.List;

public record Pacote(String mome, String descrisao, String codigo, Linguagem linguagem, List<String> arquivos, LocalDateTime dataCriacao) {
}
