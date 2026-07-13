package br.com.catalogo.mediacollectioncatalog.midia.musical.domain;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.midia.Midia;
import br.com.catalogo.mediacollectioncatalog.midia.musical.enums.CategoriaDisco;
import br.com.catalogo.mediacollectioncatalog.midia.musical.enums.GeneroMusical;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "midias_musicais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

// Evita loop infinito no toString devido a relacionamento bidirecional
@ToString(exclude = {"artista", "faixas"})
public abstract class MidiaMusical extends Midia {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroMusical genero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaDisco categoria;

    @Size(max = 250)
    @Column(nullable = false, length = 250)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

    @OneToMany(mappedBy = "midiaMusical", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("numero ASC")
    private List<Faixa> faixas = new ArrayList<>();
}
