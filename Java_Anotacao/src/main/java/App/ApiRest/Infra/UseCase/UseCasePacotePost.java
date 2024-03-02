package App.ApiRest.Infra.UseCase;

import App.ApiRest.Domain.Pacote;
import App.ApiRest.Infra.Gateway.PacoteGateway;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UseCasePacotePost {

    private final PacoteGateway pacoteGateway;

    public UseCasePacotePost(PacoteGateway pacoteGateway) {
        this.pacoteGateway = pacoteGateway;
    }

    public ResponseEntity<Pacote> NovoPacote(@RequestParam String nome, @RequestParam String descrisao,
                                             @RequestParam Linguagem linguagem, @RequestPart MultipartFile[] files) throws IOException
    {return pacoteGateway.NovoPacote(nome, descrisao, linguagem, files);}

}
