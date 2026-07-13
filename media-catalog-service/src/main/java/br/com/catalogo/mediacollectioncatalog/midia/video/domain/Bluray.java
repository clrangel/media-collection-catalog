package br.com.catalogo.mediacollectioncatalog.midia.video.domain;

import br.com.catalogo.mediacollectioncatalog.midia.video.enums.Resolucao;
import br.com.catalogo.mediacollectioncatalog.midia.video.enums.TipoEdicao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "blurays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Bluray extends MidiaVideo{

    @NotNull
    @Column(nullable = false)
    private Integer numeroDiscos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEdicao tipoEdicao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Resolucao resolucao;
}
