package br.com.catalogo.mediacollectioncatalog.midia.musical.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.MidiaMusical;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.CDMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.repository.MusicalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CDService {

    private final MusicalRepository repository;
    private final ArtistaRepository artistaRepository;
    private final CDMapper mapper;

    public CDResponseDTO cadastrarCD(CDRequestDTO dto){

        // 1. Converter DTO → Entity
        CD cd = mapper.toEntity(dto);

        // 2. Buscar artista
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        // 3. Setar artista
        cd.setArtista(artista);

        // 4. Salvar
        CD cdSalvo = repository.save(cd);

        // 5. Converter para DTO
        return mapper.toDTO(cdSalvo);
    }

    public void deletarCD(Long id){
        if (!repository.existsById(id)) {
            throw new RuntimeException("CD não encontrado");
        }
        repository.deleteById(id);
    }

    @Transactional
    public CDResponseDTO atualizarCD(Long id, CDRequestDTO dto) {

        // 1. Busca o CD existente no banco
        CD cd = (CD) repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CD não encontrado"));

        // 2. Atualiza apenas os campos vindos do DTO
        // Não recria o objeto, apenas modifica o existente
        mapper.updateFromDto(dto, cd);

        // 3. Atualiza o artista manualmente (mapper ignora esse campo)
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        cd.setArtista(artista);

        // 4. Salva o objeto atualizado
        CD cdSalvo = repository.save(cd);

        // 5. Retorna o DTO de resposta
        return mapper.toDTO(cdSalvo);
    }
}
