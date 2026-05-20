package br.com.catalogo.mediacollectioncatalog.diretor.dtos;

import br.com.catalogo.mediacollectioncatalog.midia.dtos.MidiaResumoDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record DiretorResponseDTO(

        @Schema(description = "ID do diretor", example = "1")
        Long id,

        @Schema(description = "Nome do Diretor", example = "Kleber Mendonça Filho")
        String nome,

        @Schema(description = "País da nacionalidade do diretor", example = "Brasil")
        String nacionalidade,

        @Schema(description = "Lista de mídias do diretor")
        List<MidiaResumoDTO> midias
) {}
