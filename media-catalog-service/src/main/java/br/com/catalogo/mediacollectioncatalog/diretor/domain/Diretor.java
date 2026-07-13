package br.com.catalogo.mediacollectioncatalog.diretor.domain;

import br.com.catalogo.mediacollectioncatalog.midia.video.domain.MidiaVideo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diretores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

// Evita loop infinito no toString devido a relacionamento bidirecional
@ToString(exclude = "midiasVideos")
public class Diretor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @Size(max = 30)
    @Column(length = 30)
    private String nacionalidade;

    @ManyToMany(mappedBy = "diretores", fetch = FetchType.LAZY)
    private List<MidiaVideo> midiasVideos = new ArrayList<>();
}
