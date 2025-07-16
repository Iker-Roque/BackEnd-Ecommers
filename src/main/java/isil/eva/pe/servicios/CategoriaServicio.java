package isil.eva.pe.servicios;

import java.util.List;
import isil.eva.pe.jpa.modelo.Categoria;

public interface CategoriaServicio {
    List<Categoria> listarTodos();
    Categoria guardar(Categoria categoria);
    Categoria buscarPorId(Integer id);
    void eliminar(Integer id);
}
