package br.com.catalogo.mediacollectioncatalog.artista.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaRequestDTO;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.artista.mapper.ArtistaMapper;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class ArtistaService {

    private final ArtistaRepository repository;

    public ArtistaResponseDTO cadastrarArtista(ArtistaRequestDTO dto){

        Artista artista = ArtistaMapper.toEntity(dto);

        Artista artistaSalvo = repository.save(artista);

        return ArtistaMapper.toDTO(artistaSalvo);
    }

    public ArtistaResponseDTO atualizar(Long id, ArtistaRequestDTO dto) {

        Artista artista = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Artista não encontrado com ID: " + id
                ));

        // Atualiza os dados
        ArtistaMapper.updateFromDTO(dto, artista);

        // Salva alterações
        Artista atualizado = repository.save(artista);

        return ArtistaMapper.toDTO(atualizado);
    }

    public void deletar(Long id) {

        Artista artista = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Artista não encontrado com ID: " + id
                ));

        if (!artista.getMidias().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é possível excluir artista com mídias associadas"
            );
        }

        repository.delete(artista);
    }

    public ArtistaResponseDTO buscarPorId(Long id) {

        Artista artista = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Artista não encontrado com ID: " + id
                ));

        return ArtistaMapper.toDTO(artista);
    }
}
