package br.com.catalogo.mediacollectioncatalog.midia.video.domain;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.midia.Midia;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "midias_videos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
// Evita loop infinito no toString devido a relacionamento bidirecional
@ToString(exclude = "diretores")
public class MidiaVideo extends Midia {

    @Size(max = 250)
    @Column(nullable = false, length = 250)
    private String sinopse;

    @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String atores;

    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String roteiro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroFilme genero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaVideo categoriaVideo;

    @Size(max = 30)
    @Column(length = 30)
    private String paisFilme;

    @ManyToMany(mappedBy = "midiasVideos", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Diretor> diretores = new ArrayList<>();

}
