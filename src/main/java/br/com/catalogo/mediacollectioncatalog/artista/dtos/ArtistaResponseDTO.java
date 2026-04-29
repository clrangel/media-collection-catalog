package br.com.catalogo.mediacollectioncatalog.artista.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ArtistaResponseDTO(

        @Schema(description = "ID do artista", example = "1")
        Long id,

        @Schema(description = "Nome do artista", example = "Katatonia")
        String nome,

        @Schema(description = "País de origem do artista", example = "Suécia")
        String origem,

        @Schema(description = "Lista de mídias do artista")
        List<MidiaResumoDTO> midias
) {}
