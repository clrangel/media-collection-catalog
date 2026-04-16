package br.com.catalogo.mediacollectioncatalog.midia.musical.controller;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.cddto.CDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.service.CDService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cds")
public class CDController {

    private final CDService service;

    @PostMapping
    public ResponseEntity<CDResponseDTO> cadastrarCD(@Valid @RequestBody CDRequestDTO dto){
        CDResponseDTO cdCriado = service.cadastrarCD(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cdCriado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCD(@PathVariable Long id){
        service.deletarCD(id);
        return ResponseEntity.noContent().build();
    }
}
