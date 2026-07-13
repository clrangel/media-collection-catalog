package br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaResponseDTO;

import java.util.List;

public record CDResponseDTO(

        Long id,
        String nomeArtista,
        String titulo,
        Integer anoLancamento,
        String genero,
        String categoria,
        String descricao,
        Long artistaId,

        Integer numeroDiscos,
        String formatoDisco,
        String tipoCD,

        String tipoMidia,

        List<FaixaResponseDTO>faixas
) {}
