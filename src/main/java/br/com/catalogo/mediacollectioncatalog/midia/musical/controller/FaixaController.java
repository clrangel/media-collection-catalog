package br.com.catalogo.mediacollectioncatalog.midia.musical.controller;

import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.CDMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.service.FaixaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faixas")
@RequiredArgsConstructor
public class FaixaController {

    private final FaixaService service;
    private final CDMapper mapper;

    @GetMapping
    public ResponseEntity<List<FaixaResponseDTO>> buscarFaixas(
            @RequestParam String titulo) {

        List<FaixaResponseDTO> response = service.buscarPorTitulo(titulo)
                .stream()
                .map(mapper::toFaixaDTO)
                .toList();

        return ResponseEntity.ok(response);
    }
}
