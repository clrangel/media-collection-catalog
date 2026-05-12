package br.com.catalogo.mediacollectioncatalog.midia.video.repository;

import br.com.catalogo.mediacollectioncatalog.midia.video.domain.DVD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DVDRepository extends JpaRepository<DVD, Long> {

}
