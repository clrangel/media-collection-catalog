package br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Vinil;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VinilMapper {

    // Converte do DTO de requisição para a entidade
    // Mapeia cada campo do DTO para a entidade
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "formatoDisco", target = "formatoDisco")
    @Mapping(source = "tipoVinil", target = "tipoVinil")
    Vinil toEntity(VinilRequestDTO dto);

    // Converte da entidade para o DTO de resposta
    // Mapeia da entidade para o DTO de resposta

    // Extrai o ID do objeto Artista para um campo simples no DTO
    @Mapping(source = "artista.id", target = "artistaId")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "categoria", target = "categoria")
    @Mapping(source = "formatoDisco", target = "formatoDisco")
    @Mapping(source = "tipoVinil", target = "tipoVinil")

    // Extrai o nome do objeto Artista para um campo simples no DTO
    @Mapping(source = "artista.nome", target = "nomeArtista")

    // Converte a entidade em uma representação textual do tipo de mídia (ex: Vinil)
    @Mapping(target = "tipoMidia", source = ".", qualifiedByName = "mapTipoMidia")
    @Mapping(source = "faixas", target = "faixas") // Mapeia as Faixas explicitamente
    VinilResponseDTO toDTO(Vinil vinil);

    @Named("mapTipoMidia")
    default String mapTipoMidia(Vinil vinil) {
        return "Vinil";
    }

    // Atualiza um Vinil existente com dados do DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(VinilRequestDTO dto, @MappingTarget Vinil entity);

    // Mapeamento de Faixa
    FaixaResponseDTO toFaixaDTO(Faixa faixa);

    List<FaixaResponseDTO> toFaixaDTOList(List<Faixa> faixas);

}
