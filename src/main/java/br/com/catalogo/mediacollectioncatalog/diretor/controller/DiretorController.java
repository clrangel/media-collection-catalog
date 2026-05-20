package br.com.catalogo.mediacollectioncatalog.diretor.controller;


import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorRequestDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.dtos.DiretorResponseDTO;
import br.com.catalogo.mediacollectioncatalog.diretor.service.DiretorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Diretores", description = "Operações relacionadas aos diretores")

@RequiredArgsConstructor
@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private final DiretorService service;

    @PostMapping
    @Operation(
            summary = "Cadastrar diretor",
            description = "Cria um novo diretor no sistema com nome e origem."
    )
    public ResponseEntity<DiretorResponseDTO> cadastrarDiretor(@Valid @RequestBody DiretorRequestDTO dto){
        DiretorResponseDTO diretorCriado = service.cadastrarDiretor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(diretorCriado);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar diretor",
            description = "Atualiza os dados de um diretor existente pelo ID."
    )
    public ResponseEntity<DiretorResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DiretorRequestDTO dto) {

        DiretorResponseDTO response = service.atualizar(id, dto);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar diretor",
            description = "Remove um diretor pelo ID. Não permite exclusão caso existam mídias associadas."
    )
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar diretor por ID",
            description = "Retorna os dados de um diretor incluindo suas mídias (título, ano e tipo)."
    )
    public ResponseEntity<DiretorResponseDTO> buscarPorId(@PathVariable Long id) {

        DiretorResponseDTO response = service.buscarPorId(id);

        return ResponseEntity.ok(response);
    }


    @GetMapping
    @Operation(
            summary = "Listar todos os diretores",
            description = "Retorna uma lista com todos os diretores cadastrados."
    )
    public ResponseEntity<List<DiretorResponseDTO>> buscarTodos() {

        List<DiretorResponseDTO> response = service.buscarTodos();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/buscar")
    @Operation(
            summary = "Buscar diretores por nome",
            description = "Busca diretores pelo nome (parcial e ignorando maiúsculas/minúsculas), retornando também suas mídias."
    )
    public ResponseEntity<List<DiretorResponseDTO>> buscarPorNome(
            @RequestParam String nome) {

        List<DiretorResponseDTO> response = service.buscarPorNome(nome);

        return ResponseEntity.ok(response);
    }
}
