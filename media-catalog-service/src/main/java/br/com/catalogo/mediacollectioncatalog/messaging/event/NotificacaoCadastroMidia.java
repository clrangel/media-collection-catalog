package br.com.catalogo.mediacollectioncatalog.messaging.event;

import java.time.Instant;

public record NotificacaoCadastroMidia(

        String email,
        String titulo,
        Integer anoLancamento,
        String tipoMidia,
        String artista,
        String formatoDisco,
        String tipo,
        Instant dataCadastro
) {
}
