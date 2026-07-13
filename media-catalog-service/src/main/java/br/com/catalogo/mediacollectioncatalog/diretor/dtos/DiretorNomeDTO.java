package br.com.catalogo.mediacollectioncatalog.diretor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record DiretorNomeDTO(

        @Schema(description = "Nome do diretor", example = "Christopher Nolan")
        String nome
) {}
