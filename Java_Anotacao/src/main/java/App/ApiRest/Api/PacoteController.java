package App.ApiRest.Api;

import App.ApiRest.Domain.Pacote;
import App.ApiRest.Infra.Persistence.Entity.PacoteEntity;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.UseCase.UseCasePacoteGet;
import App.ApiRest.Infra.UseCase.UseCasePacotePost;
import App.ApiRest.Infra.UseCase.UseCasePacotePut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("Pacote")
@Tag(name = "Pacote",description = "Manipula dados e arquivos da entidade Pacote")
public class PacoteController {

    private final UseCasePacoteGet useCasePacoteGet;
    private final UseCasePacotePost useCasePacotePost;
    private final UseCasePacotePut useCasePacotePut;

    public PacoteController(UseCasePacoteGet useCasePacoteGet, UseCasePacotePost useCasePacotePost, UseCasePacotePut useCasePacotePut) {
        this.useCasePacoteGet = useCasePacoteGet;
        this.useCasePacotePost = useCasePacotePost;
        this.useCasePacotePut = useCasePacotePut;
    }

    @Operation(summary = "Lista Registros", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @GetMapping(value = "/ListarPacotes")
    public ResponseEntity<List<PacoteEntity>> ListarPacotes()
    { return useCasePacoteGet.ListarPacotes();}


    @Operation(summary = "Busca Por Id o pacote", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @GetMapping(value = "/BuscarPacotePorId")
    public ResponseEntity<Pacote> BuscarPacotePorId(@RequestParam Long id)
    { return useCasePacoteGet.BuscarPacotePorId(id);}

    @Operation(summary = "Busca Por Nome o pacote", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @GetMapping(value = "/BuscarPacotePornome")
    public ResponseEntity<Pacote> BuscarPacotePornome(@RequestParam String nome)
    { return  useCasePacoteGet.BuscarPacotePornome(nome);}

    @Operation(summary = "Busca Por Codigo o pacote", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada  com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @GetMapping(value = "/BuscarPacotePorcodigo")
    public ResponseEntity<Pacote> BuscarPacotePorcodigo(@RequestParam String codigo)
    { return useCasePacoteGet.BuscarPacotePorcodigo(codigo);}

    @Operation(summary = "Busca e faz o download do pacote zipado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada  com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @GetMapping(value = "/DownloadFiles")
    public ResponseEntity<Resource> DownloadFiles(@RequestParam Long id) throws IOException
    { return useCasePacoteGet.DownloadFiles(id);}

    @Operation(summary = "Novo pacote", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salvo realizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @PostMapping(value = "/NovoPacote",consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public ResponseEntity<Pacote> NovoPacote(@RequestParam String nome, @RequestParam String descrisao,
                                             @RequestParam Linguagem linguagem, @RequestPart MultipartFile[] files) throws IOException
    { return useCasePacotePost.NovoPacote(nome, descrisao, linguagem, files);}

    //PUT

    @Operation(summary = "altera Dados do pacote", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @PutMapping(value = "/EditarInformacoes")
    public ResponseEntity<Pacote> EditarInformacoes(@RequestParam Long id, @RequestParam String nome, @RequestParam String descrisao)
    { return useCasePacotePut.EditarInformacoes(id, nome, descrisao);}

    @Operation(summary = "altera todos os arquivos ao pacote", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @PutMapping(value = "/AlterarArquivos",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Pacote> AlterarArquivos(@RequestParam Long id,@RequestPart MultipartFile[] files) throws IOException
    { return useCasePacotePut.AlterarArquivos(id, files);}


    @Operation(summary = "Adiciona Arquivo ou arquivos ao pacote", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Editação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops! algo deu errado"),
    })
    @PutMapping(value = "/AdicionarArquivos",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Pacote> AdicionarArquivos(@RequestParam Long id,@RequestPart MultipartFile[] files) throws IOException
    { return useCasePacotePut.AdicionarArquivos(id, files);}



}
