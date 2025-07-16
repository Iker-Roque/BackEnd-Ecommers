package isil.eva.pe.controladores.apirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.Carrito;
import isil.eva.pe.jpa.repositorios.CarritoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CarritoControlador {

    @Autowired
    private CarritoRepositorio carritoRepositorio;

    @GetMapping("/carritos")
    public List<Carrito> getAllCarritos() {
        return carritoRepositorio.findAll();
    }

    @GetMapping("/carritos/{id}")
    public ResponseEntity<Carrito> getCarritoPorId(@PathVariable(value = "id") Integer carritoId)
            throws RecursoNoEncontradoException {
        Carrito carrito = carritoRepositorio.findById(carritoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Carrito no se encuentra con el ID: " + carritoId));
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("/carritos")
    public Carrito crearCarrito(@RequestBody Carrito carrito) {
        return carritoRepositorio.save(carrito);
    }

    @PutMapping("/carritos/{id}")
    public ResponseEntity<Carrito> actualizarCarrito(@PathVariable(value = "id") Integer carritoId,
                                                     @Validated @RequestBody Carrito carritoDetails)
            throws RecursoNoEncontradoException {
        Carrito carrito = carritoRepositorio.findById(carritoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Carrito no encontrado con el ID: " + carritoId));

        carrito.setId_usuario(carritoDetails.getId_usuario());
        carrito.setFecha_creacion(carritoDetails.getFecha_creacion());

        return ResponseEntity.ok(carritoRepositorio.save(carrito));
    }

    @DeleteMapping("/carritos/{id}")
    public Map<String, Boolean> eliminarCarrito(@PathVariable(value = "id") Integer carritoId)
            throws RecursoNoEncontradoException {
        Carrito carrito = carritoRepositorio.findById(carritoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Carrito no se encuentra con el ID: " + carritoId));

        carritoRepositorio.delete(carrito);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
