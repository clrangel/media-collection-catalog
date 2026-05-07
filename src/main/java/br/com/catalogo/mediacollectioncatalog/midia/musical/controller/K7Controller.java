package br.com.catalogo.mediacollectioncatalog.midia.musical.controller;


import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.k7.K7RequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.k7.K7ResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.service.K7Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "K7s",
        description = "Operações relacionadas ao gerenciamento de K7s")

@RequiredArgsConstructor
@RestController
@RequestMapping("/k7s")
public class K7Controller {

    private final K7Service service;


    @Operation(
            summary = "Cadastrar novo K7",
            description = "Cria um novo K7 no sistema com base nos dados informados"
    )
    @PostMapping
    public ResponseEntity<K7ResponseDTO> cadastrarK7(@Valid @RequestBody K7RequestDTO dto){
        K7ResponseDTO k7Criado = service.cadastrarK7(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(k7Criado);
    }


    @Operation(
            summary = "Atualizar K7",
            description = "Atualiza os dados de um K7 existente no sistema com base no ID informado"
    )
    @PutMapping("/{id}")
    public ResponseEntity<K7ResponseDTO> atualizarK7(
            @PathVariable Long id,
            @Valid @RequestBody K7RequestDTO dto) {

        K7ResponseDTO atualizado = service.atualizarK7(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(
            summary = "Deletar K7",
            description = "Remove um K7 do sistema com base no ID informado"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarK7(@PathVariable Long id){
        service.deletarK7(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Buscar K7 por ID",
            description = "Retorna os dados de um K7 específico com base no ID informado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<K7ResponseDTO> buscarK7PorId(@PathVariable Long id) {
        K7ResponseDTO k7 = service.buscarK7PorId(id);
        return ResponseEntity.ok(k7);
    }
}
