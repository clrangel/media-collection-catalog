package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FaixaRepository extends JpaRepository<Faixa, Long> {

    List<Faixa> findByTituloContainingIgnoreCase(String titulo);
}
