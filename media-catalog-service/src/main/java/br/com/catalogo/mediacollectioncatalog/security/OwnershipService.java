package br.com.catalogo.mediacollectioncatalog.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OwnershipService {

    // Serviço responsável por validar se o usuário autenticado é o proprietário da mídia.
    // Criado para centralizar essa regra de autorização e evitar a repetição da mesma lógica nos Services de cada tipo de mídia.
    private final AuthenticatedUserService authenticatedUserService;

    public void validarProprietario(Long usuarioIdDaMidia) {

        Long usuarioIdAutenticado = authenticatedUserService.getUserId();

        if (!usuarioIdAutenticado.equals(usuarioIdDaMidia)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Usuário não possui permissão para alterar esta mídia"
            );
        }
    }
}
