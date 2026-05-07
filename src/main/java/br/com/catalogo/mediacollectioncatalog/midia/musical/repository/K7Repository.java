package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.K7;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface K7Repository extends JpaRepository<K7, Long> {

    @Query("""
    SELECT k FROM K7 k
    LEFT JOIN FETCH k.artista
    LEFT JOIN FETCH k.faixas
    WHERE k.id = :id
    """)
    Optional<K7> findByIdWithArtista(@Param("id") Long id);

    @Query("SELECT k FROM K7 k JOIN FETCH k.artista")
    List<K7> findAllWithArtista();
}
