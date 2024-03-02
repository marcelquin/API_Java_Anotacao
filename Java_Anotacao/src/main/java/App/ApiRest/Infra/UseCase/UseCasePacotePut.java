package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Pacote;
import App.ApiRest.Infra.Gateway.PacoteGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UseCasePacotePut {

    private final PacoteGateway pacoteGateway;

    public UseCasePacotePut(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public ResponseEntity<Pacote> EditarInformacoes(@RequestParam Long id, @RequestParam String nome, @RequestParam String descrisao)
    { return pacoteGateway.EditarInformacoes(id, nome, descrisao);  }
    public ResponseEntity<Pacote> AlterarArquivos(@RequestParam Long id,@RequestPart MultipartFile[] files) throws IOException
    { return pacoteGateway.AlterarArquivos(id, files);}
    public ResponseEntity<Pacote> AdicionarArquivos(@RequestParam Long id,@RequestPart MultipartFile[] files) throws IOException
    { return pacoteGateway.AdicionarArquivos(id, files);}



}
