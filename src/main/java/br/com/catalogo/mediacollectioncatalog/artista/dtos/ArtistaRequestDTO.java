package br.com.catalogo.mediacollectioncatalog.artista.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record ArtistaRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @Size(max = 30, message = "Origem deve ter no máximo 30 caracteres")
        String origem,

        List<Long> midiasIds
) {}
