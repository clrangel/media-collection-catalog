package br.com.catalogo.mediacollectioncatalog.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    public Long getUserId() {

        // Obtém a autenticação do usuário atualmente armazenada pelo Spring Security.
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        // Converte a autenticação para JwtAuthenticationToken,
        // permitindo acessar as informações do JWT autenticado.
        JwtAuthenticationToken jwtAuthentication =
                (JwtAuthenticationToken) authentication;

        // Obtém o JWT utilizado na autenticação da requisição.
        Jwt jwt = jwtAuthentication.getToken();

        // Recupera do JWT o ID do usuário armazenado na claim "userId".
        return jwt.getClaim("userId");
    }
}
