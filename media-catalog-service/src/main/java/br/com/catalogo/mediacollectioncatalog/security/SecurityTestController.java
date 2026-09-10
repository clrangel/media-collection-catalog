package br.com.catalogo.mediacollectioncatalog.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller utilizado exclusivamente para testar e demonstrar
// a recuperação do ID do usuário autenticado a partir do JWT.
// Não faz parte do fluxo funcional do cadastro de mídias.
@RestController
@RequiredArgsConstructor
public class SecurityTestController {

    private final AuthenticatedUserService authenticatedUserService;

    // Endpoint de teste que retorna o userId presente no JWT
    // do usuário autenticado na requisição.
    @GetMapping("/security/test-user")
    public Long testUser() {
        return authenticatedUserService.getUserId();
    }
}
