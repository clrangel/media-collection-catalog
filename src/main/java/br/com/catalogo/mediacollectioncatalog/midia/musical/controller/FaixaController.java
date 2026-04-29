package br.com.catalogo.mediacollectioncatalog.midia.musical.controller;

import br.com.catalogo.mediacollectioncatalog.mapper.FaixaMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaDetalhadaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.dto.faixadto.FaixaResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.musical.mapstruct.CDMapper;
import br.com.catalogo.mediacollectioncatalog.midia.musical.service.FaixaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Faixas", description = "Operações relacionadas às faixas musicais")
@RequestMapping("/faixas")
@RequiredArgsConstructor
public class FaixaController {

    private final FaixaService service;
    private final CDMapper mapper;
    private final FaixaMapper faixaMapper;

    @GetMapping
    public ResponseEntity<List<FaixaResponseDTO>> buscarFaixas(
            @RequestParam String titulo) {

        List<FaixaResponseDTO> response = service.buscarPorTitulo(titulo)
                .stream()
                .map(mapper::toFaixaDTO)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/detalhado")
    public ResponseEntity<List<FaixaDetalhadaResponseDTO>> buscarFaixasDetalhado(
            @RequestParam String titulo) {

        List<FaixaDetalhadaResponseDTO> response = service.buscarDetalhado(titulo)
                .stream()
                .map(faixaMapper::toDetalhadoDTO)
                .toList();

        return ResponseEntity.ok(response);
    }
}
