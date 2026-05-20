package br.com.catalogo.mediacollectioncatalog.diretor.repository;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    Optional<Diretor> findByNome(String nome);

    @Query("""
    SELECT d FROM Diretor d
    LEFT JOIN FETCH d.midiasVideos
    WHERE d.id = :id
""")
    Optional<Diretor> buscarComMidias(@Param("id") Long id);
}
