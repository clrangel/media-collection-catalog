package br.com.catalogo.mediacollectioncatalog.midia.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record MidiaBuscaDTO(

        @Schema(description = "Título da mídia", example = "Roots")
        String titulo,

        @Schema(
                description = "Tipo da mídia",
                example = "CD",
                allowableValues = {"CD", "VINIL", "K7", "DVD", "BLURAY"}
        )
        String tipoMidia,

        @Schema(
                description = "Responsável pela mídia (artista ou diretor)",
                example = "Sepultura"
        )
        String artistaOuDiretor
) {}
