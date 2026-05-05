package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Vinil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VinilRepository extends JpaRepository<Vinil, Long> {

    @Query("""
    SELECT v FROM Vinil v
    LEFT JOIN FETCH v.artista
    LEFT JOIN FETCH v.faixas
    WHERE v.id = :id
""")
    Optional<Vinil> findByIdWithArtista(@Param("id") Long id);

}
