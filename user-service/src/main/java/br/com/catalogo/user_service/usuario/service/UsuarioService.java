package br.com.catalogo.user_service.usuario.service;

import br.com.catalogo.user_service.usuario.domain.Usuario;
import br.com.catalogo.user_service.usuario.dtos.UsuarioRequestDTO;
import br.com.catalogo.user_service.usuario.dtos.UsuarioResponseDTO;
import br.com.catalogo.user_service.usuario.mapper.UsuarioMapper;
import br.com.catalogo.user_service.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
