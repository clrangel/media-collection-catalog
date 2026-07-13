package br.com.catalogo.mediacollectioncatalog.midia.musical.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "faixas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

// Evita loop infinito no toString devido a relacionamento bidirecional
@ToString(exclude = "midiaMusical")
public class Faixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Integer numero;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "midia_musical_id", nullable = false)
    private MidiaMusical midiaMusical;
}
