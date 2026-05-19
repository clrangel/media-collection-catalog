package br.com.catalogo.mediacollectioncatalog.midia.video.repository;

import br.com.catalogo.mediacollectioncatalog.midia.video.domain.DVD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DVDRepository extends JpaRepository<DVD, Long> {

    // Busca um DVD específico pelo ID já carregando os diretores
    @Query("""
        SELECT d FROM DVD d
        LEFT JOIN FETCH d.diretores
        WHERE d.id = :id
    """)
    Optional<DVD> findByIdWithDiretores(@Param("id") Long id);

    // Busca todos os DVDs já carregando os diretores
    @Query("""
        SELECT DISTINCT d FROM DVD d
        LEFT JOIN FETCH d.diretores
    """)
    List<DVD> findAllWithDiretores();
}
