package isil.eva.pe.jpa.repositorios;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import isil.eva.pe.jpa.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndContraseña(String correo, String contraseña);
    Optional<Usuario> findByCorreo(String correo);
}
