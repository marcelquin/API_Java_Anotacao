package App.ApiRest.Infra.Persistence.Entity;

import App.ApiRest.Domain.Backup;
import App.ApiRest.Infra.Persistence.Enum.OpcaoBackup;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "backup")
public class BackupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OpcaoBackup opcaoBackup;

    private String nome;

    private String descrisao;

    private String codigo;

    private List<String> arquivos;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public BackupEntity(Backup record) {
        this.opcaoBackup = record.opcaoBackup();
        this.nome = record.nome();
        this.descrisao = record.descrsao();
        this.codigo = record.codigo();
        this.arquivos = record.arquivos();
        this.dataCriacao = record.dataCriacao();
    }
}
