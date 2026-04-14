package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.MidiaMusical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicalRepository extends JpaRepository<MidiaMusical, Long> {
    Optional<MidiaMusical> findByTitulo(String titulo);
}
