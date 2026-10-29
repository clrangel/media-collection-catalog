package br.com.catalogo.mediacollectioncatalog.messaging.event;

import java.time.Instant;

public record MidiaCadastrada(
        Long midiaId,
        Long usuarioId,
        Instant dataHora
) {
}
