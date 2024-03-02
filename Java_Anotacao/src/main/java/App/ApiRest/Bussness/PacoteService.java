package App.ApiRest.Bussness;

import App.ApiRest.Domain.Pacote;
import App.ApiRest.Infra.Exceptions.EntityNotFoundException;
import App.ApiRest.Infra.Exceptions.NullargumentsException;
import App.ApiRest.Infra.Gateway.PacoteGateway;
import App.ApiRest.Infra.Persistence.Entity.ArquivoEntity;
import App.ApiRest.Infra.Persistence.Entity.PacoteEntity;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;
import App.ApiRest.Infra.Persistence.Repository.ArquivoRepository;
import App.ApiRest.Infra.Persistence.Repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.CONTENT_DISPOSITION;

@Service
public class PacoteService implements PacoteGateway {

    @Value("${App.caminhozip}")
    private String caminhozip;

    private final PacoteRepository pacoteRepository;
    private final ArquivoRepository arquivoRepository;
    private final BackupService backupService;
    private final FileServerService fileServerService;

    public PacoteService(PacoteRepository pacoteRepository, ArquivoRepository arquivoRepository, BackupService backupService, FileServerService fileServerService) {
        this.pacoteRepository = pacoteRepository;
        this.arquivoRepository = arquivoRepository;
        this.backupService = backupService;
        this.fileServerService = fileServerService;
    }

    @Override
    public ResponseEntity<List<PacoteEntity>> ListarPacotes()
    {
        try
        {
            return new ResponseEntity<>(pacoteRepository.findAll(), HttpStatus.OK);
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Pacote> BuscarPacotePorId(Long id)
    {
        try
        {
            if(id != null)
            {
                if(pacoteRepository.existsById(id))
                {
                    PacoteEntity entity = pacoteRepository.findById(id).get();
                    Pacote response = new Pacote(entity.getNome(),entity.getDescrisao(), entity.getCodigo(),
                                            entity.getLinguagem(),entity.getArquivos().getArquivos(),entity.getDataCriacao());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {throw new EntityNotFoundException(); }
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Pacote> BuscarPacotePornome(String nome)
    {
        try
        {
            if(nome != null)
            {
                if(pacoteRepository.existsBynome(nome))
                {
                    PacoteEntity entity = pacoteRepository.findBynome(nome).get();
                    Pacote response = new Pacote(entity.getNome(),entity.getDescrisao(), entity.getCodigo(),
                            entity.getLinguagem(),entity.getArquivos().getArquivos(),entity.getDataCriacao());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {throw new EntityNotFoundException(); }
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Pacote> BuscarPacotePorcodigo(String codigo)
    {
        try
        {
            if(codigo != null)
            {
                if(pacoteRepository.existsBycodigo(codigo))
                {
                    PacoteEntity entity = pacoteRepository.findBycodigo(codigo).get();
                    Pacote response = new Pacote(entity.getNome(),entity.getDescrisao(), entity.getCodigo(),
                            entity.getLinguagem(),entity.getArquivos().getArquivos(),entity.getDataCriacao());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {throw new EntityNotFoundException(); }
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Resource> DownloadFiles(Long id) throws IOException
    {
        try
        {
            if(id != null)
            {
                if(pacoteRepository.existsById(id))
                {
                   PacoteEntity entity = pacoteRepository.findById(id).get();
                    String filename = entity.getCodigo()+".zip";
                    Path filePath  = Path.of(caminhozip+filename);
                    if (!Files.exists(filePath)) {
                        throw new FileNotFoundException(filename + " was not found on the server");
                    }
                    Resource resource = new UrlResource(filePath.toUri());
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.add("File-Name", filename);
                    httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
                    return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                            .headers(httpHeaders).body(resource);
                }
                else
                {throw new EntityNotFoundException(); }
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Pacote> NovoPacote(String nome, String descrisao, Linguagem linguagem, MultipartFile[] files) throws IOException
    {
        try
        {
            if(nome != null && descrisao != null && linguagem != null && files != null)
            {
                PacoteEntity entity = new PacoteEntity();
                ArquivoEntity arquivoEntity = new ArquivoEntity();
                entity.setNome(nome);
                entity.setDescrisao(descrisao);
                entity.setLinguagem(linguagem);
                int dig = (int) (111111 + Math.random() * 999999);
                String codigo = "pack_"+dig;
                entity.setCodigo(codigo);
                entity.setDataCriacao(LocalDateTime.now());
                entity.setTimeStamp(LocalDateTime.now());
                List<String> arquivos = new ArrayList<>();
                for(MultipartFile file: files)
                {
                    arquivos.add(file.getOriginalFilename());
                }
                arquivoEntity.setTimeStamp(LocalDateTime.now());
                arquivoEntity.setArquivos(arquivos);
                arquivoRepository.save(arquivoEntity);
                entity.setArquivos(arquivoEntity);
                pacoteRepository.save(entity);
                fileServerService.Upload(codigo,files);
                backupService.NovoEvento(OpcaoBackup.PACOTE_CRIADO, entity.getNome(),entity.getDescrisao(),
                        entity.getCodigo(),entity.getLinguagem(),entity.getDataCriacao(),entity.getArquivos().getArquivos());
                Pacote response = new Pacote(entity.getNome(),entity.getDescrisao(), entity.getCodigo(),
                        entity.getLinguagem(),entity.getArquivos().getArquivos(),entity.getDataCriacao());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Pacote> EditarInformacoes(Long id, String nome, String descrisao)
    {
        try
        {
            if(id != null)
            {
                if(pacoteRepository.existsById(id))
                {
                    PacoteEntity entity = pacoteRepository.findById(id).get();
                    if(nome != null){ entity.setNome(nome);}
                    if(descrisao != null){ entity.setDescrisao(descrisao);}
                    entity.setTimeStamp(LocalDateTime.now());
                    pacoteRepository.save(entity);
                    backupService.NovoEvento(OpcaoBackup.PACOTE_EDITADO, entity.getNome(),entity.getDescrisao(),
                            entity.getCodigo(),entity.getLinguagem(),entity.getDataCriacao(),entity.getArquivos().getArquivos());
                    Pacote response = new Pacote(entity.getNome(),entity.getDescrisao(), entity.getCodigo(),
                            entity.getLinguagem(),entity.getArquivos().getArquivos(),entity.getDataCriacao());
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                else
                {throw new EntityNotFoundException(); }
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Pacote> AlterarArquivos(Long id, MultipartFile[] files) throws IOException
    {
        try
        {
            if(id != null && files != null)
            {
                if(pacoteRepository.existsById(id))
                {
                    PacoteEntity entity = pacoteRepository.findById(id).get();
                    if(arquivoRepository.existsById(entity.getArquivos().getId()))
                    {
                        ArquivoEntity arquivoEntity = arquivoRepository.findById(id).get();
                        List<String> arquivos = new ArrayList<>();
                        for (MultipartFile file:files)
                        {
                            arquivos.add(file.getOriginalFilename());
                        }
                        arquivoEntity.setArquivos(arquivos);
                        arquivoEntity.setTimeStamp(LocalDateTime.now());
                        arquivoRepository.save(arquivoEntity);
                        backupService.NovoEvento(OpcaoBackup.PACOTE_EDITADO, entity.getNome(),entity.getDescrisao(),
                                entity.getCodigo(),entity.getLinguagem(),entity.getDataCriacao(),entity.getArquivos().getArquivos());
                        fileServerService.Update(entity.getCodigo(), entity.getArquivos().getArquivos(), files);
                        Pacote response = new Pacote(entity.getNome(),entity.getDescrisao(), entity.getCodigo(),
                                entity.getLinguagem(),entity.getArquivos().getArquivos(),entity.getDataCriacao());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    else
                    {throw new EntityNotFoundException(); }
                }
                else
                {throw new EntityNotFoundException(); }
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    @Override
    public ResponseEntity<Pacote> AdicionarArquivos(Long id, MultipartFile[] files) throws IOException
    {
        try
        {
            if(id != null && files != null)
            {
                if(pacoteRepository.existsById(id))
                {
                    PacoteEntity entity = pacoteRepository.findById(id).get();
                    if(arquivoRepository.existsById(entity.getArquivos().getId()))
                    {
                        ArquivoEntity arquivoEntity = arquivoRepository.findById(id).get();
                        List<String> arquivos = new ArrayList<>();
                        for (MultipartFile file:files)
                        {
                            arquivos.add(file.getOriginalFilename());
                        }
                        arquivoEntity.getArquivos().addAll(arquivos);
                        arquivoEntity.setTimeStamp(LocalDateTime.now());
                        arquivoRepository.save(arquivoEntity);
                        backupService.NovoEvento(OpcaoBackup.PACOTE_EDITADO, entity.getNome(),entity.getDescrisao(),
                                entity.getCodigo(),entity.getLinguagem(),entity.getDataCriacao(),entity.getArquivos().getArquivos());
                        fileServerService.AddFile(entity.getCodigo(), files);
                        Pacote response = new Pacote(entity.getNome(),entity.getDescrisao(), entity.getCodigo(),
                                entity.getLinguagem(),entity.getArquivos().getArquivos(),entity.getDataCriacao());
                        return new ResponseEntity<>(response, HttpStatus.OK);
                    }
                    else
                    {throw new EntityNotFoundException(); }
                }
                else
                {throw new EntityNotFoundException(); }
            }
            else
            {throw new NullargumentsException();}
        }catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
