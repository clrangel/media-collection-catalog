package br.com.catalogo.mediacollectioncatalog.midia.video.controller;


import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.bluraydto.BluRayResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.service.BlurayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Blu-rays",
        description = "Operações relacionadas ao gerenciamento de BLURAYs")

@RequiredArgsConstructor
@RestController
@RequestMapping("/blurays")
public class BlurayController {

    private final BlurayService service;


    @Operation(
            summary = "Cadastrar novo Bluray",
            description = "Cria um novo Bluray no sistema com base nos dados informados"
    )
    @PostMapping
    public ResponseEntity<BluRayResponseDTO> cadastrarBluray(@Valid @RequestBody BluRayRequestDTO dto){
        BluRayResponseDTO blurayCriado = service.cadastrarBluray(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(blurayCriado);
    }


    @Operation(
            summary = "Atualizar Bluray",
            description = "Atualiza os dados de um Bluray existente no sistema com base no ID informado"
    )
    @PutMapping("/{id}")
    public ResponseEntity<BluRayResponseDTO> atualizarBluray(@PathVariable Long id, @Valid @RequestBody BluRayRequestDTO dto) {

        BluRayResponseDTO atualizado = service.atualizarBluray(id, dto);
        return ResponseEntity.ok(atualizado);
    }



    @Operation(
            summary = "Deletar Bluray",
            description = "Remove um Bluray do sistema com base no ID informado"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarBluray(@PathVariable Long id){
        service.deletarBluray(id);
        return ResponseEntity.noContent().build();
    }



    @Operation(
            summary = "Buscar Bluray por ID",
            description = "Retorna os dados de um Bluray específico com base no ID informado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<BluRayResponseDTO> buscarBlurayPorId(@PathVariable Long id) {
        BluRayResponseDTO bluray = service.buscarBlurayPorId(id);
        return ResponseEntity.ok(bluray);
    }



    @Operation(
            summary = "Listar todos os Blurays",
            description = "Retorna uma lista com todos os Blurays cadastrados no sistema"
    )
    @GetMapping
    public ResponseEntity<List<BluRayResponseDTO>> listarTodosBlurays() {
        List<BluRayResponseDTO> blurays = service.listarTodosBlurays();
        return ResponseEntity.ok(blurays);
    }
}
