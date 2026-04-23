package br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CDMapper {

    // Converte do DTO de requisição para a entidade
    // Mapeia cada campo do DTO para a entidade
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "formatoDisco", target = "formatoDisco")
    @Mapping(source = "tipoCD", target = "tipoCD")
    CD toEntity(CDRequestDTO dto);

    // Converte da entidade para o DTO de resposta
    // Mapeia da entidade para o DTO de resposta

    // Extrai o ID do objeto Artista para um campo simples no DTO
    @Mapping(source = "artista.id", target = "artistaId")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "formatoDisco", target = "formatoDisco")
    @Mapping(source = "tipoCD", target = "tipoCD")

    // Extrai o nome do objeto Artista para um campo simples no DTO
    @Mapping(source = "artista.nome", target = "nomeArtista")

    // Converte a entidade em uma representação textual do tipo de mídia (ex: CD)
    @Mapping(target = "tipoMidia", source = ".", qualifiedByName = "mapTipoMidia")
    CDResponseDTO toDTO(CD cd);

    @Named("mapTipoMidia")
    default String mapTipoMidia(CD cd) {
        return "CD";
    }

    // Atualiza um CD existente com dados do DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CDRequestDTO dto, @MappingTarget CD entity);

    // MapStruct gera a implementação automaticamente
    List<CDResponseDTO> toDTOList(List<CD> cds);
}