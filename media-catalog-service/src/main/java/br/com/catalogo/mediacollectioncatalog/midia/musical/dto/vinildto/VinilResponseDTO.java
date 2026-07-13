package br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaResponseDTO;

import java.util.List;

public record VinilResponseDTO(

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
        String tipoVinil,

        String tipoMidia,

        List<FaixaResponseDTO> faixas
) {}
