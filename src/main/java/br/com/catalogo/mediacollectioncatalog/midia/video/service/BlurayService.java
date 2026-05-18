package br.com.catalogo.mediacollectioncatalog.midia.video.service;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.diretor.repository.DiretorRepository;
import br.com.catalogo.mediacollectioncatalog.midia.video.domain.Bluray;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.mapstruct.BlurayMapper;
import br.com.catalogo.mediacollectioncatalog.midia.video.repository.BlurayRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlurayService {

    private final BlurayRepository repository;
    private final DiretorRepository diretorRepository;
    private final BlurayMapper mapper;


    @Transactional
    public BluRayResponseDTO cadastrarBluray(BluRayRequestDTO dto){

        // 1. Converter DTO → Entity
        // O MapStruct converte automaticamente os campos simples
        Bluray bluray = mapper.toEntity(dto);

        // 2. Buscar todos os diretores pelos IDs enviados no DTO
        // O método findAllById retorna uma lista de diretores encontrados
        List<Diretor> diretores = diretorRepository.findAllById(dto.diretoresIds());

        // 3. Validar se todos os diretores existem
        // Se a quantidade encontrada for diferente da enviada,
        // significa que algum ID não existe no banco
        if (diretores.size() != dto.diretoresIds().size()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Um ou mais diretores não foram encontrados"
            );
        }

        // 4. Associar os diretores ao Bluray
        // Como o relacionamento é ManyToMany,
        // basta adicionar a lista de diretores na entidade
        bluray.setDiretores(diretores);

        // 5. Salvar o Bluray no banco
        // O Hibernate persiste a entidade e a tabela de relacionamento
        Bluray bluraySalvo = repository.save(bluray);

        // 6. Converter Entity → ResponseDTO
        // O mapper transforma a entidade no retorno da API
        return mapper.toDTO(bluraySalvo);
    }

}
