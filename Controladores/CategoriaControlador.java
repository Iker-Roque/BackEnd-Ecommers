package isil.eva.pe.controladores.apirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.Categoria;
import isil.eva.pe.jpa.repositorios.CategoriaRepositorio;

@RestController
@RequestMapping("/api/v1")
public class CategoriaControlador {

    @Autowired
    private CategoriaRepositorio categoriaRepository;

    // GET: obtener todas las categorías
    @GetMapping("/categorias")
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // GET: obtener una categoría por ID
    @GetMapping("/categorias/{id}")
    public ResponseEntity<Categoria> getCategoriaPorId(@PathVariable(value = "id") Integer categoriaId)
            throws RecursoNoEncontradoException {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no se encuentra por ID: " + categoriaId));
        return ResponseEntity.ok(categoria);
    }

    // POST: crear nueva categoría
    @PostMapping("/categorias")
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // PUT: actualizar una categoría existente
    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable(value = "id") Integer categoriaId,
                                                         @Validated @RequestBody Categoria categoriaDetails)
            throws RecursoNoEncontradoException {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con el ID: " + categoriaId));

        categoria.setNombre(categoriaDetails.getNombre());

        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    // DELETE: eliminar categoría por ID
    @DeleteMapping("/categorias/{id}")
    public Map<String, Boolean> eliminarCategoria(@PathVariable(value = "id") Integer categoriaId)
            throws RecursoNoEncontradoException {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no se encuentra con el ID: " + categoriaId));

        categoriaRepository.delete(categoria);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
