package br.com.catalogo.user_service.usuario.mapper;

import br.com.catalogo.user_service.usuario.domain.Usuario;
import br.com.catalogo.user_service.usuario.dtos.UsuarioRequestDTO;
import br.com.catalogo.user_service.usuario.dtos.UsuarioResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toResponseDTO(Usuario usuario);
}
