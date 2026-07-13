package br.com.catalogo.mediacollectioncatalog.midia.musical.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "k7s")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class K7 extends MidiaMusical{
}
