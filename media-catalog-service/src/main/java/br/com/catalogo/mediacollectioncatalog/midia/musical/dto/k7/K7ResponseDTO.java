package br.com.catalogo.mediacollectioncatalog.midia.musical.dto.k7;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaResponseDTO;

import java.util.List;

public record K7ResponseDTO(

        Long id,
        String nomeArtista,
        String titulo,
        Integer anoLancamento,
        String genero,
        String categoria,
        String descricao,
        Long artistaId,

        String tipoMidia,

        List<FaixaResponseDTO> faixas
) {}
