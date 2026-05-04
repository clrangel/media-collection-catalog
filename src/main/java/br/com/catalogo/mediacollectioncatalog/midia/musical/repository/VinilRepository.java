package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Vinil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinilRepository extends JpaRepository<Vinil, Long> {
}
