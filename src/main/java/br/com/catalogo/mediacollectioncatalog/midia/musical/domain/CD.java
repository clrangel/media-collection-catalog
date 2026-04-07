package br.com.catalogo.mediacollectioncatalog.midia.musical.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "cds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class CD extends MidiaMusical{

    @NotNull
    @Column(nullable = false)
    private Integer numeroDiscos;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatoDisco formatoDisco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCD tipoCD;
}
