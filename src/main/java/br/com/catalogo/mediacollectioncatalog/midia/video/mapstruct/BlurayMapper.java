package br.com.catalogo.mediacollectioncatalog.midia.video.mapstruct;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.midia.video.domain.Bluray;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BlurayMapper {

    // DTO -> Entity
    Bluray toEntity(BluRayRequestDTO dto);

    // Entity -> DTO
    @Mapping(target = "tipoMidia", source = ".", qualifiedByName = "mapTipoMidia")
    @Mapping(target = "diretores", source = "diretores")
    BluRayResponseDTO toDTO(Bluray bluray);

    @Named("mapTipoMidia")
    default String mapTipoMidia(Bluray bluray) {

        return "BLURAY";
    }

    // Converte lista de Diretores em lista de nomes
    default List<String> mapDiretores(List<Diretor> diretores) {
        return diretores.stream()
                .map(Diretor::getNome)
                .toList();
    }
}
