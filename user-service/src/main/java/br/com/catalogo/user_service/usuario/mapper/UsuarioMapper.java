package br.com.catalogo.user_service.usuario.mapper;

import br.com.catalogo.user_service.usuario.domain.Usuario;
import br.com.catalogo.user_service.usuario.dtos.UsuarioRequestDTO;
import br.com.catalogo.user_service.usuario.dtos.UsuarioResponseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioRequestDTO dto);

    UsuarioResponseDTO toDTO(Usuario usuario);

    List<UsuarioResponseDTO> toDTOList(List<Usuario> usuarios);

    // Atualiza um usuário existente com dados do DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(UsuarioRequestDTO dto, @MappingTarget Usuario entity);
}
