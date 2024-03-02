package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Pacote;
import App.ApiRest.Infra.Gateway.PacoteGateway;
import App.ApiRest.Infra.Persistence.Entity.PacoteEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

public class UseCasePacoteGet {

    private final PacoteGateway pacoteGateway;

    public UseCasePacoteGet(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public ResponseEntity<List<PacoteEntity>> ListarPacotes()
    { return  pacoteGateway.ListarPacotes();}
    public ResponseEntity<Pacote> BuscarPacotePorId(@RequestParam Long id)
    { return pacoteGateway.BuscarPacotePorId(id);}
    public ResponseEntity<Pacote> BuscarPacotePornome(@RequestParam String nome)
    { return  pacoteGateway.BuscarPacotePornome(nome);}
    public ResponseEntity<Pacote> BuscarPacotePorcodigo(@RequestParam String codigo)
    { return pacoteGateway.BuscarPacotePorcodigo(codigo);}
    public ResponseEntity<Resource> DownloadFiles(Long id) throws IOException
    { return pacoteGateway.DownloadFiles(id);}
}
