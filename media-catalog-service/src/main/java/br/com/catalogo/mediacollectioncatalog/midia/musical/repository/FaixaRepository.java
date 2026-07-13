package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FaixaRepository extends JpaRepository<Faixa, Long> {

    List<Faixa> findByTituloContainingIgnoreCase(String titulo);

    @Query("""
    SELECT f FROM Faixa f
    JOIN FETCH f.midiaMusical m
    JOIN FETCH m.artista a
    WHERE LOWER(f.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))
""")
    List<Faixa> buscarDetalhadoPorTitulo(@Param("titulo") String titulo);
}
