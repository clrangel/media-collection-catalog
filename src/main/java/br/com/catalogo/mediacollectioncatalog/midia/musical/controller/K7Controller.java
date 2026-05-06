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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
