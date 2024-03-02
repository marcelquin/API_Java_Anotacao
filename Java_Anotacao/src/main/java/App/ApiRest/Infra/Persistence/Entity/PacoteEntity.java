package App.ApiRest.Infra.Persistence.Entity;

import App.ApiRest.Domain.Pacote;
import App.ApiRest.Infra.Persistence.Enum.Linguagem;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "Pacote")
public class PacoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descrisao;

    @Enumerated(EnumType.STRING)
    private Linguagem linguagem;

    @OneToOne
    @JoinColumn(name = "arquivoEntity_id",referencedColumnName = "id")
    private ArquivoEntity arquivos;

    private String codigo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCriacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public PacoteEntity(Pacote record) {
        this.nome = record.mome();
        this.descrisao = record.descrisao();
    }
}
