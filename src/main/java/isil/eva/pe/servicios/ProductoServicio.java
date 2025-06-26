package isil.eva.pe.servicios;

import java.util.List;
import isil.eva.pe.jpa.modelo.*;

public interface ProductoServicio {
	  List<Producto> listarTodos();
	  Producto guardar(Producto producto);
	  Producto buscarPorId(Integer id);
	  void eliminar(Integer id);
}
