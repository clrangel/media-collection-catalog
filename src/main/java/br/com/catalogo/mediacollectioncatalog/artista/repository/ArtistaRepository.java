package br.com.catalogo.mediacollectioncatalog.artista.repository;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNome(String nome);

    @Query("""
    SELECT a FROM Artista a
    LEFT JOIN FETCH a.midias
    WHERE a.id = :id
""")
    Optional<Artista> buscarComMidias(@Param("id") Long id);
}
