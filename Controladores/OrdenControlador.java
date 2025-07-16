package isil.eva.pe.controladores.apirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.Orden;
import isil.eva.pe.jpa.repositorios.OrdenRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrdenControlador {

    @Autowired
    private OrdenRepositorio ordenesRepositorio;

    @GetMapping("/ordenes")
    public List<Orden> getAllOrdenes() {
        return ordenesRepositorio.findAll();
    }

    @GetMapping("/ordenes/{id}")
    public ResponseEntity<Orden> getOrdenPorId(@PathVariable(value = "id") Integer ordenId)
            throws RecursoNoEncontradoException {
        Orden orden = ordenesRepositorio.findById(ordenId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no se encuentra con el ID: " + ordenId));
        return ResponseEntity.ok(orden);
    }

    @PostMapping("/ordenes")
    public Orden crearOrden(@RequestBody Orden orden) {
        return ordenesRepositorio.save(orden);
    }

    @PutMapping("/ordenes/{id}")
    public ResponseEntity<Orden> actualizarOrden(@PathVariable(value = "id") Integer ordenId,
                                                   @Validated @RequestBody Orden ordenDetails)
            throws RecursoNoEncontradoException {
        Orden orden = ordenesRepositorio.findById(ordenId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no encontrada con el ID: " + ordenId));

        orden.setId_usuario(ordenDetails.getId_usuario());
        orden.setFecha(ordenDetails.getFecha());
        orden.setTotal(ordenDetails.getTotal());

        return ResponseEntity.ok(ordenesRepositorio.save(orden));
    }

    @DeleteMapping("/ordenes/{id}")
    public Map<String, Boolean> eliminarOrden(@PathVariable(value = "id") Integer ordenId)
            throws RecursoNoEncontradoException {
        Orden orden = ordenesRepositorio.findById(ordenId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Orden no se encuentra con el ID: " + ordenId));

        ordenesRepositorio.delete(orden);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
