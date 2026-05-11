package br.com.catalogo.mediacollectioncatalog.diretor.repository;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiretorRepository extends JpaRepository<Diretor, Long> {
    Optional<Diretor> findByNome(String nome);
}
