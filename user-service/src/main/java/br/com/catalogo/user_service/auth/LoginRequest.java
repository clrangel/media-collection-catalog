package br.com.catalogo.user_service.auth;

public record LoginRequest(

        String email,
        String senha
) {}
