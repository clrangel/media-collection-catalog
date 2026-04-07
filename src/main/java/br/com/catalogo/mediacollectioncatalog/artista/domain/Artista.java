package br.com.catalogo.mediacollectioncatalog.artista.domain;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.MidiaMusical;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

// Evita loop infinito no toString devido a relacionamento bidirecional
@ToString(exclude = "midias")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @Size(max = 30)
    @Column(length = 30)
    private String origem;

    @OneToMany(mappedBy = "artista")
    private List<MidiaMusical> midias = new ArrayList<>();
}
