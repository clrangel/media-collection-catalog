package br.com.catalogo.mediacollectioncatalog.midia.video.mapstruct;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.midia.video.domain.DVD;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.DVDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.DVDResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DVDMapper {

    // DTO -> Entity
    DVD toEntity(DVDRequestDTO dto);

    // Entity -> DTO
    @Mapping(target = "tipoMidia", source = ".", qualifiedByName = "mapTipoMidia")
    @Mapping(target = "diretores", source = "diretores")
    DVDResponseDTO toDTO(DVD dvd);

    @Named("mapTipoMidia")
    default String mapTipoMidia(DVD dvd) {
        return "DVD";
    }

    // Converte lista de Diretores em lista de nomes
    default List<String> mapDiretores(List<Diretor> diretores) {
        return diretores.stream()
                .map(Diretor::getNome)
                .toList();
    }

    // Atualiza um DVD existente com dados do DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(DVDRequestDTO dto, @MappingTarget DVD entity);

    // MapStruct gera a implementação automaticamente
    List<DVDResponseDTO> toDTOList(List<DVD> dvds);
}
