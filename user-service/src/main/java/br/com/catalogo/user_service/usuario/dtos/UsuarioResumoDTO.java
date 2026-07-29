package br.com.catalogo.user_service.usuario.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record UsuarioResumoDTO(

        @Schema(description = "ID do usuário", example = "1")
        Long id,

        @Schema(description = "Nome do usuário", example = "Kleber Mendonça Filho")
        String nome,

        @Schema(description = "Email do usuário", example = "klebemf@gmail.com")
        String email,

        @Schema(
                description = "Data do cadastro do usuário",
                example = "2026-07-29T18:30:00Z"
        )
        Instant dataCadastro
) {}
