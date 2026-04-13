package br.com.catalogo.mediacollectioncatalog.artista.dtos;

import java.util.List;

public record ArtistaResponseDTO(

        Long id,
        String nome,
        String origem,
        List<Long> midiasIds
) {}
