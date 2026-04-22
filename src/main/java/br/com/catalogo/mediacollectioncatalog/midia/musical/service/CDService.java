package br.com.catalogo.mediacollectioncatalog.midia.musical.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.CDMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.repository.CDRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CDService {

    private final CDRepository repository;
    private final ArtistaRepository artistaRepository;
    private final CDMapper mapper;

    public CDResponseDTO cadastrarCD(CDRequestDTO dto){

        // 1. Converter DTO → Entity
        CD cd = mapper.toEntity(dto);

        // 2. Buscar artista
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Artista não encontrado com o ID: " + dto.artistaId()
                ));

        // 3. Setar artista
        cd.setArtista(artista);

        // 4. Salvar
        CD cdSalvo = repository.save(cd);

        // 5. Converter para DTO
        return mapper.toDTO(cdSalvo);
    }

    public void deletarCD(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "CD não encontrado com o ID: " + id
            );
        }
        repository.deleteById(id);
    }

    @Transactional
    public CDResponseDTO atualizarCD(Long id, CDRequestDTO dto) {

        // 1. Busca o CD existente no banco
        CD cd = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "CD não encontrado com o ID: " + id
                ));

        // 2. Atualiza apenas os campos vindos do DTO
        // Não recria o objeto, apenas modifica o existente
        mapper.updateFromDto(dto, cd);

        // 3. Atualiza o artista manualmente (mapper ignora esse campo)
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "CD não encontrado com o ID: " + id
                ));

        cd.setArtista(artista);

        // 4. Salva o objeto atualizado
        CD cdSalvo = repository.save(cd);

        // 5. Retorna o DTO de resposta
        return mapper.toDTO(cdSalvo);
    }

    public CDResponseDTO buscarCDPorId(Long id) {

        CD cd = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "CD não encontrado com o ID: " + id
                ));

        return mapper.toDTO(cd);
    }

    public List<CDResponseDTO> listarTodosCDs() {
        List<CD> cds = repository.findAll();

        return mapper.toDTOList(cds);
    }
}
