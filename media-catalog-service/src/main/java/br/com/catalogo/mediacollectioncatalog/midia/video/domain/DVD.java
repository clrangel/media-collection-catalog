package br.com.catalogo.mediacollectioncatalog.midia.video.domain;

import br.com.catalogo.mediacollectioncatalog.midia.video.enums.TipoEdicao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "dvds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DVD extends MidiaVideo{

    @NotNull
    @Column(nullable = false)
    private Integer numeroDiscos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEdicao tipoEdicao;
}
