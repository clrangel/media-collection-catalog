package br.com.catalogo.mediacollectioncatalog.artista.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ArtistaRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        @Schema(description = "Nome do artista", example = "Katatonia")
        String nome,

        @Size(max = 30, message = "Origem deve ter no máximo 30 caracteres")
        @Schema(description = "País de origem do artista", example = "Suécia")
        String origem,

        @Schema(description = "IDs das mídias associadas ao artista", example = "[1, 2, 3]")
        List<Long> midiasIds
) {}
