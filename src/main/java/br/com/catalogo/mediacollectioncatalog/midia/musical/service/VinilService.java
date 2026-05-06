package br.com.catalogo.mediacollectioncatalog.midia.musical.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.MidiaMusical;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Vinil;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.VinilMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.repository.VinilRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class VinilService {

    private final VinilRepository repository;
    private final ArtistaRepository artistaRepository;
    private final VinilMapper mapper;

    public VinilResponseDTO cadastrarVinil(VinilRequestDTO dto){

        // 1. Converter DTO → Entity
        Vinil vinil = mapper.toEntity(dto);

        // 2. Buscar artista
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Artista não encontrado com o ID: " + dto.artistaId()
                ));

        // 3. Setar artista
        vinil.setArtista(artista);

        // 4. Adiciona Faixas <-Parse
        List<Faixa> faixas = parseFaixas(dto.faixasTexto(), vinil);
        vinil.setFaixas(faixas);

        // 5. Salvar
        Vinil vinilSalvo = repository.save(vinil);

        // 6. Converter para DTO
        return mapper.toDTO(vinilSalvo);
    }

    @Transactional
    public VinilResponseDTO atualizarVinil(Long id, VinilRequestDTO dto) {

        // 1. Busca o Vinil existente no banco
        Vinil vinil = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vinil não encontrado com o ID: " + id
                ));

        // 2. Atualiza apenas os campos vindos do DTO
        // Não recria o objeto, apenas modifica o existente
        mapper.updateFromDto(dto, vinil);

        // 3. Atualiza o artista manualmente (mapper ignora esse campo)
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Artista não encontrado com o ID: " + dto.artistaId()
                ));

        vinil.setArtista(artista);

        // 4. Atualizar faixas (se vier no DTO)
        if (dto.faixasTexto() != null) {
            vinil.getFaixas().clear();

            List<Faixa> novasFaixas = parseFaixas(dto.faixasTexto(), vinil);

            vinil.getFaixas().addAll(novasFaixas);
        }

        // 5. Salva o objeto atualizado
        Vinil vinilSalvo = repository.save(vinil);

        // 6. Retorna o DTO de resposta
        return mapper.toDTO(vinilSalvo);
    }

    public void deletarVinil(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Vinil não encontrado com o ID: " + id
            );
        }
        repository.deleteById(id);
    }

    public VinilResponseDTO buscarVinilPorId(Long id) {

        Vinil vinil = repository.findByIdWithArtista(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vinil não encontrado com o ID: " + id
                ));

        return mapper.toDTO(vinil);
    }

    public List<VinilResponseDTO> listarTodosVinis() {
        List<Vinil> vinis = repository.findAllWithArtista();

        return mapper.toDTOList(vinis);
    }

    // Parser de faixas: transforma texto em lista de entidades Faixa
    // Converte uma string de faixas (uma por linha, opcionalmente numeradas)
    // em uma lista de entidades Faixa associadas à mídia musical
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
