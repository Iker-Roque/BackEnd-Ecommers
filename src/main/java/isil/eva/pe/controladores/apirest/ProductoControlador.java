package isil.eva.pe.controladores.apirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.Producto;
import isil.eva.pe.jpa.repositorios.ProductoRepositorio;

@RestController
@RequestMapping("/api/v1")
public class ProductoControlador {

    @Autowired // inyección de dependencias
    private ProductoRepositorio productoRepository;

    @GetMapping("productos") // consulta y devuelve todos los productos
    private List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }
    
 // Traer productos por categoría
    @GetMapping("/productos/categoria/{idCategoria}")
    public List<Producto> getProductosPorCategoria(@PathVariable Integer idCategoria) {
        return productoRepository.findByCategoria_IdCategoria(idCategoria);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoPorId(@PathVariable(value = "id") Integer productoId)
            throws RecursoNoEncontradoException {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no se encuentra por ID: " + productoId));
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable(value = "id") Integer productoId,
                                                       @Validated @RequestBody Producto productoDetails)
            throws RecursoNoEncontradoException {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no ha sido encontrado por el ID : " + productoId));

        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setStock(productoDetails.getStock());
        producto.setCategoria(productoDetails.getCategoria());

        return ResponseEntity.ok(productoRepository.save(producto));
    }

    
    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }
    
    
    @DeleteMapping("/productos/{id}")
    public Map<String, Boolean> eliminarProducto(@PathVariable(value = "id") Integer productoId)
            throws RecursoNoEncontradoException {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no se encuentra con el ID: " + productoId));

        productoRepository.delete(producto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
