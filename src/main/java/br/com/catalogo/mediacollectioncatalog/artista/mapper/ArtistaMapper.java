package br.com.catalogo.mediacollectioncatalog.artista.mapper;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaRequestDTO;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.Midia;

public class ArtistaMapper {

    // Utilizando nesse início o Mapper de forma manual.
    public static ArtistaResponseDTO toDTO(Artista artista) {
        return new ArtistaResponseDTO(
                artista.getId(),
                artista.getNome(),
                artista.getOrigem(),
                artista.getMidias()
                        .stream()
                        .map(Midia::getId)
                        .toList()
        );
    }

    public static Artista toEntity(ArtistaRequestDTO dto) {
        Artista artista = new Artista();

        artista.setNome(dto.nome());
        artista.setOrigem(dto.origem());

        // midias NÃO são setadas aqui (fica para a service)

        return artista;
    }
}
