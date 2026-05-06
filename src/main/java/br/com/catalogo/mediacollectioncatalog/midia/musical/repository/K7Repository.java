package br.com.catalogo.mediacollectioncatalog.midia.musical.repository;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.K7;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface K7Repository extends JpaRepository<K7, Long> {

    Optional<K7> findByTitulo(String titulo);
}
