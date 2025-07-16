package isil.eva.pe.jpa.repositorios; 

import org.springframework.data.jpa.repository.JpaRepository;
import isil.eva.pe.jpa.modelo.Producto; 

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

}