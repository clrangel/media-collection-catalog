package br.com.catalogo.mediacollectioncatalog.midia.video.controller;


import br.com.catalogo.mediacollectioncatalog.midia.video.dto.DVDRequestDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.dto.DVDResponseDTO;
import br.com.catalogo.mediacollectioncatalog.midia.video.service.DVDService;
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
        name = "DVDs",
        description = "Operações relacionadas ao gerenciamento de DVDs")

@RequiredArgsConstructor
@RestController
@RequestMapping("/dvds")
public class DVDController {

    private final DVDService service;

    @Operation(
            summary = "Cadastrar novo DVD",
            description = "Cria um novo DVD no sistema com base nos dados informados"
    )
    @PostMapping
    public ResponseEntity<DVDResponseDTO> cadastrarDVD(@Valid @RequestBody DVDRequestDTO dto){
        DVDResponseDTO dvdCriado = service.cadastrarDVD(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dvdCriado);
    }
}
