package br.com.catalogo.mediacollectioncatalog.artista.controller;

import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaNomeDTO;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaRequestDTO;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.artista.service.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Artistas", description = "Operações relacionadas aos artistas")

@RequiredArgsConstructor
@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService service;

    @PostMapping
    @Operation(
            summary = "Cadastrar artista",
            description = "Cria um novo artista no sistema com nome e origem."
    )
    public ResponseEntity<ArtistaResponseDTO> cadastrarArtista(@Valid @RequestBody ArtistaRequestDTO dto){
        ArtistaResponseDTO artistaCriado = service.cadastrarArtista(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaCriado);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar artista",
            description = "Atualiza os dados de um artista existente pelo ID."
    )
    public ResponseEntity<ArtistaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ArtistaRequestDTO dto) {

        ArtistaResponseDTO response = service.atualizar(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar artista",
            description = "Remove um artista pelo ID. Não permite exclusão caso existam mídias associadas."
    )
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar artista por ID",
            description = "Retorna os dados de um artista incluindo suas mídias (título, ano e tipo)."
    )
    public ResponseEntity<ArtistaResponseDTO> buscarPorId(@PathVariable Long id) {

        ArtistaResponseDTO response = service.buscarPorId(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os artistas",
            description = "Retorna uma lista com todos os artistas cadastrados."
    )
    public ResponseEntity<List<ArtistaResponseDTO>> buscarTodos() {

        List<ArtistaResponseDTO> response = service.buscarTodos();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar")
    @Operation(
            summary = "Buscar artistas por nome",
            description = "Busca artistas pelo nome (parcial e ignorando maiúsculas/minúsculas), retornando também suas mídias."
    )
    public ResponseEntity<List<ArtistaResponseDTO>> buscarPorNome(
            @RequestParam String nome) {

        List<ArtistaResponseDTO> response = service.buscarPorNome(nome);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/origem")
    @Operation(
            summary = "Buscar artistas por origem",
            description = "Retorna uma lista de artistas filtrados pela origem (país), exibindo apenas o nome ordenado alfabeticamente."
    )
    public ResponseEntity<List<ArtistaNomeDTO>> buscarPorOrigem(
            @RequestParam String origem) {

        List<ArtistaNomeDTO> response = service.buscarPorOrigem(origem);

        return ResponseEntity.ok(response);
    }
}
