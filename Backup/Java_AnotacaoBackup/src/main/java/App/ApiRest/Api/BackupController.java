package App.ApiRest.Api;

import App.ApiRest.Domain.Backup;
import App.ApiRest.Infra.Persistence.Entity.BackupEntity;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;
import App.ApiRest.Infra.UseCase.UseCaseBackupGet;
import App.ApiRest.Infra.UseCase.UseCaseBackupPost;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("Backup")
@Tag(name = "Backup", description = "Manipula eventos de backup referentes ao Java_Anotações")
public class BackupController {

    private final UseCaseBackupGet caseBackupGet;
    private final UseCaseBackupPost caseBackupPost;

    public BackupController(UseCaseBackupGet caseBackupGet, UseCaseBackupPost caseBackupPost) {
        this.caseBackupGet = caseBackupGet;
        this.caseBackupPost = caseBackupPost;
    }

    @Operation(summary = "Salva Novo Registro na tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca Reaizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarEventos")
    public ResponseEntity<List<BackupEntity>> ListarEventos()
    { return  caseBackupGet.ListarEventos();}

    @Operation(summary = "Salva Novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoEvento")
    public ResponseEntity<Backup> NovoEvento(@RequestParam OpcaoBackup opcaoBackup, @RequestParam String nome,
                                             @RequestParam String descrsao, @RequestParam String codigo,
                                             @RequestParam Linguagem linguagem, @RequestParam LocalDateTime dataCriacao,
                                             @RequestParam List<String> arquivos)
    { return caseBackupPost.NovoEvento(opcaoBackup, nome, descrsao, codigo, linguagem, dataCriacao, arquivos);}


}
