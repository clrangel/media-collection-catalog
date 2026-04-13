package br.com.catalogo.mediacollectioncatalog.artista.repository;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNome(String nome);
}
