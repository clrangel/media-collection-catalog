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
            @RequestBody DiretorRequestDTO dto) {

        DiretorResponseDTO response = service.atualizar(id, dto);

        return ResponseEntity.ok(response);
    }
}
