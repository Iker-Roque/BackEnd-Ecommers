package isil.eva.pe.controladores.apirest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import isil.eva.pe.excepciones.RecursoNoEncontradoException;
import isil.eva.pe.jpa.modelo.Usuario;
import isil.eva.pe.jpa.repositorios.UsuarioRepositorio;

@RestController
@RequestMapping("/api/v1")
public class UsuarioControlador {

    @Autowired // Inyección de dependencias
    private UsuarioRepositorio usuarioRepository;

    @GetMapping("usuarios") // Consulta y devuelve todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable(value = "id") Integer usuarioId)
            throws RecursoNoEncontradoException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no se encuentra por ID: " + usuarioId));
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable(value = "id") Integer usuarioId,
                                                     @Validated @RequestBody Usuario usuarioDetails)
            throws RecursoNoEncontradoException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no ha sido encontrado por el ID : " + usuarioId));

        usuario.setNombre(usuarioDetails.getNombre());
        usuario.setCorreo(usuarioDetails.getCorreo());
        usuario.setTelefono(usuarioDetails.getTelefono());

        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public Map<String, Boolean> eliminarUsuario(@PathVariable(value = "id") Integer usuarioId)
            throws RecursoNoEncontradoException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no se encuentra con el ID: " + usuarioId));

        usuarioRepository.delete(usuario);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return response;
    }
}
