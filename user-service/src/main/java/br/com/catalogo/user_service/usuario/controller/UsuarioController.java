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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @Operation(
            summary = "Atualizar usuário",
            description = "Atualiza os dados de um usuário existente no sistema com base no ID informado"
    )
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {

        UsuarioResponseDTO atualizado = service.atualizarUsuario(id, dto);
        return ResponseEntity.ok(atualizado);
    }


    @Operation(
            summary = "Deletar Usuário",
            description = "Remove um Usuário do sistema com base no ID informado"
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
        service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
            summary = "Buscar usuário por ID",
            description = "Retorna os dados de um usuário com base no ID informado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(
            @PathVariable Long id) {

        UsuarioResponseDTO usuario = service.buscarUsuarioPorId(id);

        return ResponseEntity.ok(usuario);
    }


    @Operation(
            summary = "Listar todos os usuários",
            description = "Retorna uma lista com todos os usuários cadastrados no sistema"
    )
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> buscarTodosUsuarios() {

        List<UsuarioResponseDTO> usuarios = service.buscarTodosUsuarios();

        return ResponseEntity.ok(usuarios);
    }
}
