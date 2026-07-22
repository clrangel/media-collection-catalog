package br.com.catalogo.user_service.usuario.repository;

import br.com.catalogo.user_service.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
