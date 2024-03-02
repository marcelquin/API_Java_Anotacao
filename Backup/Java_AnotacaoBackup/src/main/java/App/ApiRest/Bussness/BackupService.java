package App.ApiRest.Bussness;

import App.ApiRest.Domain.Backup;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Gateway.BackupGateway;
import App.ApiRest.Infra.Persistence.Entity.BackupEntity;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;
import App.ApiRest.Infra.Persistence.Repository.BackupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BackupService implements BackupGateway {

    private final BackupRepository repository;

    public BackupService(BackupRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<List<BackupEntity>> ListarEventos()
    {
       try
       {
            return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
       }
       catch (Exception e)
       {
           e.getMessage();
       }
       return null;
    }

    @Override
    public ResponseEntity<Backup> NovoEvento(@RequestParam OpcaoBackup opcaoBackup, @RequestParam String nome,
                                             @RequestParam String descrsao, @RequestParam String codigo,
                                             @RequestParam Linguagem linguagem, @RequestParam LocalDateTime dataCriacao,
                                             @RequestParam List<String> arquivos)
    {
        try
        {
            if(opcaoBackup != null && nome != null && descrsao != null && codigo != null &&
                linguagem != null && dataCriacao != null && arquivos != null)
            {
                Backup response = new Backup(opcaoBackup, nome, descrsao, codigo, linguagem, dataCriacao, arquivos);
                BackupEntity entity = new BackupEntity(response);
                entity.setTimeStamp(LocalDateTime.now());
                repository.save(entity);
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }
            else
            {throw  new NullargumentsException(); }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
