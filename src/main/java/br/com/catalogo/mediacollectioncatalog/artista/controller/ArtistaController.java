package br.com.catalogo.mediacollectioncatalog.artista.controller;

import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaRequestDTO;
import br.com.catalogo.mediacollectioncatalog.artista.dtos.ArtistaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.artista.service.ArtistaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Artistas", description = "Operações relacionadas aos artistas")

@RequiredArgsConstructor
@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService service;

    @PostMapping
    public ResponseEntity<ArtistaResponseDTO> cadastrarArtista(@Valid @RequestBody ArtistaRequestDTO dto){
        ArtistaResponseDTO artistaCriado = service.cadastrarArtista(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaCriado);
    }
}
