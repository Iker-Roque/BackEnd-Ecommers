package isil.eva.pe.jpa.repositorios; 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import isil.eva.pe.jpa.modelo.Producto; 

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
	List<Producto> findByCategoria_IdCategoria(Integer idCategoria);
}