package br.com.catalogo.user_service.usuario.service;

import br.com.catalogo.user_service.usuario.domain.Usuario;
import br.com.catalogo.user_service.usuario.dtos.UsuarioRequestDTO;
import br.com.catalogo.user_service.usuario.dtos.UsuarioResponseDTO;
import br.com.catalogo.user_service.usuario.dtos.UsuarioResumoDTO;
import br.com.catalogo.user_service.usuario.mapper.UsuarioMapper;
import br.com.catalogo.user_service.usuario.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final UsuarioMapper mapper;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO dto){

        Usuario usuario = mapper.toEntity(dto);

        Usuario usuarioSalvo = repository.save(usuario);

        return mapper.toDTO(usuarioSalvo);
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO dto) {

        // 1. Busca o usuário existente no banco
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + id
                ));

        // 2. Atualiza apenas os campos vindos do DTO
        // Não recria o objeto, apenas modifica o existente
        mapper.updateFromDto(dto, usuario);

        // 3. Salva o objeto atualizado
        Usuario usuarioSalvo = repository.save(usuario);

        // 4. Retorna o DTO de resposta
        return mapper.toDTO(usuarioSalvo);
    }

    @Transactional
    public void deletarUsuario(Long id){
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Usuário não encontrado com o ID: " + id
            );
        }
        repository.deleteById(id);
    }

    @Transactional
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado com o ID: " + id
                ));

        return mapper.toDTO(usuario);
    }

    @Transactional
    public List<UsuarioResponseDTO> buscarTodosUsuarios() {

        List<Usuario> usuarios = repository.findAll();

        return mapper.toDTOList(usuarios);
    }

    @Transactional
    public UsuarioResumoDTO buscarUsuarioPorEmail(String email) {

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado com o email: " + email
                ));

        return mapper.toResumoDTO(usuario);
    }
}
