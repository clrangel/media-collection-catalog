package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CDRepository extends JpaRepository<CD, Long> {
}
