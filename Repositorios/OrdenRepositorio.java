package isil.eva.pe.jpa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import isil.eva.pe.jpa.modelo.Orden;

public interface OrdenRepositorio extends JpaRepository<Orden, Integer> {
}
