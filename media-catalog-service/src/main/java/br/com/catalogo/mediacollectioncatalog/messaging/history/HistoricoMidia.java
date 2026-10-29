package br.com.catalogo.mediacollectioncatalog.messaging.history;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "historico_midia")
@Getter
@Setter
@NoArgsConstructor
public class HistoricoMidia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "midia_id", nullable = false)
    private Long midiaId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "data_hora", nullable = false)
    private Instant dataHora;
}