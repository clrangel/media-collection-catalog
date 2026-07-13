package br.com.catalogo.mediacollectioncatalog.midia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MidiaRepository extends JpaRepository<Midia, Long> {

    @Query("""
        SELECT m FROM Midia m
        WHERE LOWER(m.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))
    """)
    List<Midia> buscarPorTitulo(@Param("titulo") String titulo);
}
