package br.com.catalogo.mediacollectioncatalog.midia.musical.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.CDMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.repository.MusicalRepository;
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
}
