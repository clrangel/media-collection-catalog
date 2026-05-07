package br.com.catalogo.mediacollectioncatalog.midia.musical.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.K7;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.MidiaMusical;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.k7.K7RequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.k7.K7ResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.K7Mapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.repository.K7Repository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class K7Service {

    private final K7Repository repository;
    private final ArtistaRepository artistaRepository;
    private final K7Mapper mapper;

    @Transactional
    public K7ResponseDTO cadastrarK7(K7RequestDTO dto){

        // 1. Converter DTO → Entity
        K7 k7 = mapper.toEntity(dto);

        // 2. Buscar artista
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Artista não encontrado com o ID: " + dto.artistaId()
                ));

        // 3. Setar artista
        k7.setArtista(artista);

        // 4. Adiciona Faixas <-Parse
        List<Faixa> faixas = parseFaixas(dto.faixasTexto(), k7);
        k7.setFaixas(faixas);

        // 5. Salvar
        K7 k7Salvo = repository.save(k7);

        // 6. Converter para DTO
        return mapper.toDTO(k7Salvo);
    }

    @Transactional
    public K7ResponseDTO atualizarK7(Long id, K7RequestDTO dto) {

        // 1. Busca o K7 existente no banco
        K7 k7 = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "K7 não encontrado com o ID: " + id
                ));

        // 2. Atualiza apenas os campos vindos do DTO
        // Não recria o objeto, apenas modifica o existente
        mapper.updateFromDto(dto, k7);

        // 3. Atualiza o artista manualmente (mapper ignora esse campo)
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Artista não encontrado com o ID: " + dto.artistaId()
                ));

        k7.setArtista(artista);

        // 4. Atualizar faixas (se vier no DTO)
        if (dto.faixasTexto() != null) {
            k7.getFaixas().clear();

            List<Faixa> novasFaixas = parseFaixas(dto.faixasTexto(), k7);

            k7.getFaixas().addAll(novasFaixas);
        }

        // 5. Salva o objeto atualizado
        K7 k7Salvo = repository.save(k7);

        // 6. Retorna o DTO de resposta
        return mapper.toDTO(k7Salvo);
    }

    @Transactional
    public void deletarK7(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "K7 não encontrado com o ID: " + id
            );
        }
        repository.deleteById(id);
    }

    // Parser de faixas: transforma texto em lista de entidades Faixa
    private List<Faixa> parseFaixas(String faixasTexto, MidiaMusical midia) {

        List<Faixa> faixas = new ArrayList<>();

        if (faixasTexto == null || faixasTexto.isBlank()) {
            return faixas;
        }

        String[] linhas = faixasTexto.split("\\n");

        int numero = 1;

        for (String linha : linhas) {

            String titulo = linha.trim();

            // Remove numeração tipo "1. ", "2 - ", etc.
            titulo = titulo.replaceAll("^\\d+[\\.\\-\\s]+", "");

            if (titulo.isBlank()) continue;

            Faixa faixa = new Faixa();
            faixa.setNumero(numero++);
            faixa.setTitulo(titulo);
            faixa.setMidiaMusical(midia);

            faixas.add(faixa);
        }

        return faixas;
    }
}
