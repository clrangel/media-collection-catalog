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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "name = Blu-rays",
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
}
