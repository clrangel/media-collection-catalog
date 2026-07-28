package br.com.catalogo.user_service.usuario.controller;

import br.com.catalogo.user_service.usuario.dtos.UsuarioRequestDTO;
import br.com.catalogo.user_service.usuario.dtos.UsuarioResponseDTO;
import br.com.catalogo.user_service.usuario.service.UsuarioService;
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
        name = "Usuários",
        description = "Operações relacionadas ao gerenciamento de usuários")

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Operation(
            summary = "Cadastrar novo Usuário",
            description = "Cria um novo Usuário no sistema com base nos dados informados"
    )
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO dto){
        UsuarioResponseDTO usuarioCriado = service.cadastrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }
}
