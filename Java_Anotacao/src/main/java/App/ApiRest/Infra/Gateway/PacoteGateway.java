package App.ApiRest.Infra.Gateway;

import App.ApiRest.Domain.Pacote;
import App.ApiRest.Infra.Persistence.Entity.PacoteEntity;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PacoteGateway {

    //get
    public ResponseEntity<List<PacoteEntity>> ListarPacotes();
    public ResponseEntity<Pacote> BuscarPacotePorId(@RequestParam Long id);
    public ResponseEntity<Pacote> BuscarPacotePornome(@RequestParam String nome);
    public ResponseEntity<Pacote> BuscarPacotePorcodigo(@RequestParam String codigo);
    public ResponseEntity<Resource> DownloadFiles(Long id) throws IOException;

    //Post
    public ResponseEntity<Pacote> NovoPacote(@RequestParam String nome, @RequestParam String descrisao,
                                             @RequestParam Linguagem linguagem, @RequestPart MultipartFile[] files) throws IOException;

    //PUT
    public ResponseEntity<Pacote> EditarInformacoes(@RequestParam Long id, @RequestParam String nome, @RequestParam String descrisao);
    public ResponseEntity<Pacote> AlterarArquivos(@RequestParam Long id,@RequestPart MultipartFile[] files) throws IOException;
    public ResponseEntity<Pacote> AdicionarArquivos(@RequestParam Long id,@RequestPart MultipartFile[] files) throws IOException;


}
