package br.com.catalogo.user_service.usuario.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        @Schema(description = "Nome do usuário", example = "Kleber Mendonça Filho")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        @Size(max = 150, message = "Email deve ter no máximo 150 caracteres")
        @Schema(description = "Email do usuário", example = "klebemf@gmail.com")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(max = 255, message = "Senha deve ter no máximo 255 caracteres")
        @Schema(description = "Senha do usuário", example = "123abc")
        String senha
) {}
