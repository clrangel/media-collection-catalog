package br.com.catalogo.mediacollectioncatalog.midia.musical.controller;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.service.VinilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Vinis",
        description = "Operações relacionadas ao gerenciamento de Viniss")

@RequiredArgsConstructor
@RestController
@RequestMapping("/vinis")
public class VinilController {

    private final VinilService service;

    @Operation(
            summary = "Cadastrar novo Vinil",
            description = "Cria um novo Vinil no sistema com base nos dados informados"
    )
    @PostMapping
    public ResponseEntity<VinilResponseDTO> cadastrarVinil(@Valid @RequestBody VinilRequestDTO dto){
        VinilResponseDTO vinilCriado = service.cadastrarVinil(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vinilCriado);
    }

    @Operation(
            summary = "Atualizar Vinil",
            description = "Atualiza os dados de um Vinil existente no sistema com base no ID informado"
    )
    @PutMapping("/{id}")
    public ResponseEntity<VinilResponseDTO> atualizarVinil(
            @PathVariable Long id,
            @Valid @RequestBody VinilRequestDTO dto) {

        VinilResponseDTO atualizado = service.atualizarVinil(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
            summary = "Deletar Vinil",
            description = "Remove um Vinil do sistema com base no ID informado"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarVinil(@PathVariable Long id){
        service.deletarVinil(id);
        return ResponseEntity.noContent().build();
    }
}
