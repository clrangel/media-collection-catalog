package br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto;

public record CDResponseDTO(

        Long id,
        String titulo,
        Integer anoLancamento,
        String genero,
        String categoria,
        String descricao,
        Long artistaId,

        Integer numeroDiscos,
        String formatoDisco,
        String tipoCD,

        String tipoMidia
) {}
