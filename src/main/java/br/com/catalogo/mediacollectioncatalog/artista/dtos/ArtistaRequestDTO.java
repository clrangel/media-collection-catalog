package br.com.catalogo.mediacollectioncatalog.artista.dtos;

import java.util.List;

public record ArtistaRequestDTO(

        String nome,
        String origem,
        List<Long> midiasIds
) {}
