package br.com.catalogo.mediacollectioncatalog.midia.video.repository;

import br.com.catalogo.mediacollectioncatalog.midia.video.domain.Bluray;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BlurayRepository extends JpaRepository<Bluray, Long> {

    // Busca um Bluray específico pelo ID já carregando os diretores
    @Query("""
        SELECT b FROM Bluray b
                    LEFT JOIN FETCH b.diretores
                    WHERE b.id = :id
    """)
    Optional<Bluray> findByIdWithDiretores(@Param("id") Long id);


    // Busca todos os Blurays já carregando os diretores
    @Query("""
        SELECT DISTINCT b FROM Bluray b
        LEFT JOIN FETCH b.diretores
    """)
    List<Bluray> findAllWithDiretores();
}
