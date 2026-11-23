package br.com.catalogo.mediacollectioncatalog.messaging.event;

import java.time.Instant;
import java.util.Map;

public record NotificacaoCadastroMidia(

        String email,
        String titulo,
        Integer anoLancamento,
        String tipoMidia,

        Map<String, String> detalhes,
        Instant dataCadastro
) {
}
