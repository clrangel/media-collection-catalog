package br.com.catalogo.mediacollectioncatalog.messaging.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoMidiaRepository
        extends JpaRepository<HistoricoMidia, Long> {
}