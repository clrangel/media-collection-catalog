package br.com.catalogo.mediacollectioncatalog.midia.video.service;

import br.com.catalogo.mediacollectioncatalog.diretor.domain.Diretor;
import br.com.catalogo.mediacollectioncatalog.diretor.repository.DiretorRepository;
import br.com.catalogo.mediacollectioncatalog.midia.video.domain.Bluray;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.mapstruct.BlurayMapper;
import br.com.catalogo.mediacollectioncatalog.midia.video.repository.BlurayRepository;
import br.com.catalogo.mediacollectioncatalog.security.AuthenticatedUserService;
import br.com.catalogo.mediacollectioncatalog.security.OwnershipService;
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

    private final AuthenticatedUserService authenticatedUserService;
    private final OwnershipService ownershipService;


    @Transactional
    public BluRayResponseDTO cadastrarBluray(BluRayRequestDTO dto){

        // 1. Converter DTO → Entity
        // O MapStruct converte automaticamente os campos simples
        Bluray bluray = mapper.toEntity(dto);

        // 2. Recuperar o ID do usuário autenticado
        Long usuarioId = authenticatedUserService.getUserId();

        // 3. Associar o Bluray ao usuário autenticado
        bluray.setUsuarioId(usuarioId);

        // 4. Buscar todos os diretores pelos IDs enviados no DTO
        // O método findAllById retorna uma lista de diretores encontrados
        List<Diretor> diretores = diretorRepository.findAllById(dto.diretoresIds());

        // 5. Validar se todos os diretores existem
        // Se a quantidade encontrada for diferente da enviada,
        // significa que algum ID não existe no banco
        if (diretores.size() != dto.diretoresIds().size()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Um ou mais diretores não foram encontrados"
            );
        }

        // 6. Associar os diretores ao Bluray
        // Como o relacionamento é ManyToMany,
        // basta adicionar a lista de diretores na entidade
        bluray.setDiretores(diretores);

        // 7. Salvar o Bluray no banco
        // O Hibernate persiste a entidade e a tabela de relacionamento
        Bluray bluraySalvo = repository.save(bluray);

        // 8. Converter Entity → ResponseDTO
        // O mapper transforma a entidade no retorno da API
        return mapper.toDTO(bluraySalvo);
    }


    @Transactional
    public BluRayResponseDTO atualizarBluray(Long id, BluRayRequestDTO dto){

        // 1. Busca o Bluray existente
        Bluray bluray = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Bluray não encontrado com o ID: " + id
                ));

        // 2. Valida se o usuário autenticado é o proprietário do Bluray
        ownershipService.validarProprietario(bluray.getUsuarioId());

        // 3. Atualiza os campos simples
        // titulo, sinopse, genero, etc.
        mapper.updateFromDto(dto, bluray);

        // 4. Atualiza os diretores manualmente
        if (dto.diretoresIds() != null) {

            // Busca todos os diretores enviados
            List<Diretor> diretores = diretorRepository.findAllById(dto.diretoresIds());

            // Valida se todos existem
            if (diretores.size() != dto.diretoresIds().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Um ou mais diretores não foram encontrados");
            }

            // Substitui a lista antiga
            bluray.setDiretores(diretores);
        }

        // 5. Salva atualização
        Bluray blurayAtualizado = repository.save(bluray);

        // 6. Retorna DTO
        return mapper.toDTO(blurayAtualizado);
    }


    @Transactional
    public void deletarBluray(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bluray não encontrado com o ID: " + id
            );
        }
        repository.deleteById(id);
    }


    public BluRayResponseDTO buscarBlurayPorId(Long id) {

        Bluray bluray = repository.findByIdWithDiretores(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Bluray não encontrado com o ID: " + id
                ));

        return mapper.toDTO(bluray);
    }

    public List<BluRayResponseDTO> listarTodosBlurays() {
        List<Bluray> blurays = repository.findAllWithDiretores();

        return mapper.toDTOList(blurays);
    }
}
