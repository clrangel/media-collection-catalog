package br.com.catalogo.mediacollectioncatalog.midia.musical.controller;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.service.CDService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CDs",
        description = "Operações relacionadas ao gerenciamento de CDs")

@RequiredArgsConstructor
@RestController
@RequestMapping("/cds")
public class CDController {

    private final CDService service;

    @Operation(
            summary = "Cadastrar novo CD",
            description = "Cria um novo CD no sistema com base nos dados informados"
    )
    @PostMapping
    public ResponseEntity<CDResponseDTO> cadastrarCD(@Valid @RequestBody CDRequestDTO dto){
        CDResponseDTO cdCriado = service.cadastrarCD(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cdCriado);
    }


    @Operation(
            summary = "Deletar CD",
            description = "Remove um CD do sistema com base no ID informado"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCD(@PathVariable Long id){
        service.deletarCD(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Atualizar CD",
            description = "Atualiza os dados de um CD existente no sistema com base no ID informado"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CDResponseDTO> atualizarCD(
            @PathVariable Long id,
            @Valid @RequestBody CDRequestDTO dto) {

        CDResponseDTO atualizado = service.atualizarCD(id, dto);
        return ResponseEntity.ok(atualizado);
    }


    @Operation(
            summary = "Buscar CD por ID",
            description = "Retorna os dados de um CD específico com base no ID informado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CDResponseDTO> buscarCDPorId(@PathVariable Long id) {
        CDResponseDTO cd = service.buscarCDPorId(id);
        return ResponseEntity.ok(cd);
    }


    @Operation(
            summary = "Listar todos os CDs",
            description = "Retorna uma lista com todos os CDs cadastrados no sistema"
    )
    @GetMapping
    public ResponseEntity<List<CDResponseDTO>> listarTodosCDs() {
        List<CDResponseDTO> cds = service.listarTodosCDs();
        return ResponseEntity.ok(cds);
    }
}
