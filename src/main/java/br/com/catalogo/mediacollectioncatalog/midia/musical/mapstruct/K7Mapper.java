package br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct;

import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.K7;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.k7.K7RequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.k7.K7ResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface K7Mapper {

    // Converte do DTO de requisição para a entidade
    // Mapeia cada campo do DTO para a entidade
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "categoria", target = "categoria")
    K7 toEntity(K7RequestDTO dto);

    // Converte da entidade para o DTO de resposta
    // Mapeia da entidade para o DTO de resposta

    // Extrai o ID do objeto Artista para um campo simples no DTO
    @Mapping(source = "artista.id", target = "artistaId")
    @Mapping(source = "genero", target = "genero")
    @Mapping(source = "categoria", target = "categoria")

    // Extrai o nome do objeto Artista para um campo simples no DTO
    @Mapping(source = "artista.nome", target = "nomeArtista")

    // Converte a entidade em uma representação textual do tipo de mídia (ex: CD)
    @Mapping(target = "tipoMidia", source = ".", qualifiedByName = "mapTipoMidia")
    @Mapping(source = "faixas", target = "faixas") // Mapeia as Faixas explicitamente
    K7ResponseDTO toDTO(K7 k7);

    @Named("mapTipoMidia")
    default String mapTipoMidia(K7 k7) {
        return "K7";
    }

    // Mapeamento de Faixa
    FaixaResponseDTO toFaixaDTO(Faixa faixa);

    List<FaixaResponseDTO> toFaixaDTOList(List<Faixa> faixas);
}
