package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CDRepository extends JpaRepository<CD, Long> {

    @Query("SELECT c FROM CD c JOIN FETCH c.artista WHERE c.id = :id")
    Optional<CD> findByIdWithArtista(@Param("id") Long id);

    @Query("SELECT c FROM CD c JOIN FETCH c.artista")
    List<CD> findAllWithArtista();
}
