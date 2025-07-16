package isil.eva.pe.jpa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import isil.eva.pe.jpa.modelo.Usuario; 


public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	
}
