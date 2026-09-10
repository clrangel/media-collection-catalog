package br.com.catalogo.user_service.auth;

import br.com.catalogo.user_service.usuario.domain.Usuario;
import br.com.catalogo.user_service.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtEncoder jwtEncoder;

    private final UsuarioRepository usuarioRepository;

    public String authenticate(LoginRequest request) {

        // Cria o objeto contendo as credenciais informadas no login.
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                );

        // Delega ao Spring Security a responsabilidade de autenticar o usuário.
        authenticationManager.authenticate(authenticationToken);

        // Busca o usuário autenticado pelo e-mail informado no login.
        // O ID recuperado será utilizado posteriormente para identificá-lo no JWT.
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException(
                        "Usuário não encontrado após autenticação."
                ));

        // Gera o JWT após a autenticação bem-sucedida.
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(request.email())
                .claim("userId", usuario.getId())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        // Define o cabeçalho do JWT informando que o token
        // será assinado utilizando o algoritmo HMAC SHA-256 (HS256).
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256)
                .build();

        // Codifica e assina o JWT utilizando o JwtEncoder configurado.
        return jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)
        ).getTokenValue();
    }
}
