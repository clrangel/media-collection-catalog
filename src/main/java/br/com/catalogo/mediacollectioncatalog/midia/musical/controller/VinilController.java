package br.com.catalogo.mediacollectioncatalog.midia.musical.controller;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.vinildto.VinilResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.service.VinilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
