package isil.eva.pe.servicios.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import isil.eva.pe.jpa.modelo.Categoria;
import isil.eva.pe.jpa.repositorios.CategoriaRepositorio;
import isil.eva.pe.servicios.CategoriaServicio;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {
    
    @Autowired
    private CategoriaRepositorio categoriaRepository;

    @Override
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
