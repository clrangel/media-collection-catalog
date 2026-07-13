package br.com.catalogo.mediacollectioncatalog.artista.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record ArtistaNomeDTO(

        @Schema(description = "Nome do artista", example = "Katatonia")
        String nome
) {}
