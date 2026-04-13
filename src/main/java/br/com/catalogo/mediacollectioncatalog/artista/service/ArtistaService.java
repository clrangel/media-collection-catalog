package br.com.catalogo.mediacollectioncatalog.artista.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaRequestDTO;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.artista.mapper.ArtistaMapper;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArtistaService {

    private final ArtistaRepository repository;

    public ArtistaResponseDTO cadastrarArtista(ArtistaRequestDTO dto){

        Artista artista = ArtistaMapper.toEntity(dto);

        Artista artistaSalvo = repository.save(artista);

        return ArtistaMapper.toDTO(artistaSalvo);
    }
}
