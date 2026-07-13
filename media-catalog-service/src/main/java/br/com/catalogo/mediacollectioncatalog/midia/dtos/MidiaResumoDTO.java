package br.com.catalogo.mediacollectioncatalog.midia.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record MidiaResumoDTO(

        @Schema(description = "Título da mídia", example = "Viva Emptiness")
        String titulo,

        @Schema(description = "Ano de lançamento", example = "2003")
        Integer anoLancamento,

        @Schema(description = "Tipo da mídia",
                example = "CD",
                allowableValues = {"CD", "VINIL", "K7", "DVD", "BLURAY"})
        String tipoMidia
) {}
