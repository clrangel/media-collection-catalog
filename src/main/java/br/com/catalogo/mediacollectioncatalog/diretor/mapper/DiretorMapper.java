package br.com.catalogo.mediacollectioncatalog.diretor.mapper;

import br.com.catalogo.mediacollectioncatalog.midia.dtos.MidiaResumoDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorRequestDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.Midia;

public class DiretorMapper {

    // Utilizando nesse início o Mapper de forma manual.
    public static DiretorResponseDTO toDTO(Diretor diretor) {
        return new DiretorResponseDTO(
                diretor.getId(),
                diretor.getNome(),
                diretor.getNacionalidade(),
                diretor.getMidiasVideos()
                        .stream()
                        .map(m -> new MidiaResumoDTO(
                                m.getTitulo(),
                                m.getAnoLancamento(),
                                mapTipoMidia(m)
                        ))
                        .toList()
        );
    }

    public static Diretor toEntity(DiretorRequestDTO dto) {
        Diretor diretor = new Diretor();

        diretor.setNome(dto.nome());
        diretor.setNacionalidade(dto.nacionalidade());

        // midias NÃO são setadas aqui (fica para a service)

        return diretor;
    }

    // Método Auxiliar
    private static String mapTipoMidia(Midia m) {
        return m.getClass().getSimpleName().toUpperCase();
    }

    // Atualiza uma entidade existente com dados do DTO (evita criar novo objeto)
    public static void updateFromDTO(DiretorRequestDTO dto, Diretor diretor) {
        if (dto.nome() != null) {
            diretor.setNome(dto.nome());
        }
        if (dto.nacionalidade() != null) {
            diretor.setNacionalidade(dto.nacionalidade());
        }
    }

}
