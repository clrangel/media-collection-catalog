package br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CDMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artista", ignore = true)
    @Mapping(target = "genero", expression = "java(GeneroMusical.valueOf(dto.genero()))")
    @Mapping(target = "categoria", expression = "java(CategoriaDisco.valueOf(dto.categoria()))")
    @Mapping(target = "formatoDisco", expression = "java(FormatoDisco.valueOf(dto.formatoDisco()))")
    @Mapping(target = "tipoCD", expression = "java(TipoCD.valueOf(dto.tipoCD()))")
    CD toEntity(CDRequestDTO dto);

    @Mapping(target = "artistaId", source = "artista.id")
    @Mapping(target = "genero", expression = "java(cd.getGenero().name())")
    @Mapping(target = "categoria", expression = "java(cd.getCategoria().name())")
    @Mapping(target = "formatoDisco", expression = "java(cd.getFormatoDisco().name())")
    @Mapping(target = "tipoCD", expression = "java(cd.getTipoCD().name())")
    @Mapping(target = "tipoMidia",
            expression = "java(cd.getClass().getSimpleName())")
    CDResponseDTO toDTO(CD cd);
}