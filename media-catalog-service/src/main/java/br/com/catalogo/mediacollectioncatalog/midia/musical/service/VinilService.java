package br.com.catalogo.mediacollectioncatalog.midia.musical.service;

import br.com.catalogo.mediacollectioncatalog.artista.domain.Artista;
import br.com.catalogo.mediacollectioncatalog.artista.repository.ArtistaRepository;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.CD;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Faixa;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.MidiaMusical;
import br.com.catalogo.mediacollectioncatalog.midia.musical.domain.Vinil;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.VinilMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.repository.VinilRepository;
import br.com.catalogo.mediacollectioncatalog.security.AuthenticatedUserService;
import br.com.catalogo.mediacollectioncatalog.security.OwnershipService;
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

    private final AuthenticatedUserService authenticatedUserService;
    private final OwnershipService ownershipService;

    @Transactional
    public VinilResponseDTO cadastrarVinil(VinilRequestDTO dto){

        // 1. Converter DTO → Entity
        Vinil vinil = mapper.toEntity(dto);

        // 2. Recuperar o ID do usuário autenticado
        Long usuarioId = authenticatedUserService.getUserId();

        // 3. Associar o Vinil ao usuário autenticado
        vinil.setUsuarioId(usuarioId);

        // 4. Buscar artista
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Artista não encontrado com o ID: " + dto.artistaId()
                ));

        // 5. Setar artista
        vinil.setArtista(artista);

        // 6. Adiciona Faixas <-Parse
        List<Faixa> faixas = parseFaixas(dto.faixasTexto(), vinil);
        vinil.setFaixas(faixas);

        // 7. Salvar
        Vinil vinilSalvo = repository.save(vinil);

        // 8. Converter para DTO
        return mapper.toDTO(vinilSalvo);
    }

    @Transactional
    public VinilResponseDTO atualizarVinil(Long id, VinilRequestDTO dto) {

        // 1. Busca o Vinil existente no banco
        Vinil vinil = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Vinil não encontrado com o ID: " + id
                ));

        // 2. Valida se o usuário autenticado é o proprietário do Vinil
        ownershipService.validarProprietario(vinil.getUsuarioId());

        // 3. Atualiza apenas os campos vindos do DTO
        // Não recria o objeto, apenas modifica o existente
        mapper.updateFromDto(dto, vinil);

        // 4. Atualiza o artista manualmente (mapper ignora esse campo)
        Artista artista = artistaRepository.findById(dto.artistaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Artista não encontrado com o ID: " + dto.artistaId()
                ));

        vinil.setArtista(artista);

        // 5. Atualizar faixas (se vier no DTO)
        if (dto.faixasTexto() != null) {
            vinil.getFaixas().clear();

            List<Faixa> novasFaixas = parseFaixas(dto.faixasTexto(), vinil);

            vinil.getFaixas().addAll(novasFaixas);
        }

        // 6. Salva o objeto atualizado
        Vinil vinilSalvo = repository.save(vinil);

        // 7. Retorna o DTO de resposta
        return mapper.toDTO(vinilSalvo);
    }

    @Transactional
    public void deletarVinil(Long id){

        // 1. Busca o Vinil existente no banco
        Vinil vinil = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vinil não encontrado com o ID: " + id
                ));

        // 2. Valida se o usuário autenticado é o proprietário do Vinil
        ownershipService.validarProprietario(vinil.getUsuarioId());

        // 3. Exclui o Vinil
        repository.delete(vinil);
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
