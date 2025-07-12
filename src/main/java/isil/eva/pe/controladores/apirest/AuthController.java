package isil.eva.pe.controladores.apirest;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import isil.eva.pe.jpa.modelo.Usuario;
import isil.eva.pe.jpa.repositorios.UsuarioRepository;
import isil.eva.pe.dto.LoginRequest;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> encontrado = usuarioRepository.findByCorreoAndContraseña(request.getCorreo(), request.getContraseña());
        if (encontrado.isPresent()) {
            return ResponseEntity.ok(encontrado.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario request) {
        if (request.getNombre() == null || request.getNombre().isBlank() ||
            request.getCorreo() == null || request.getCorreo().isBlank() ||
            request.getContraseña() == null || request.getContraseña().isBlank()) {
            return ResponseEntity.badRequest()
                .body(Map.of("mensaje", "Nombre, correo y contraseña son obligatorios"));
        }

        Optional<Usuario> existente = usuarioRepository.findByCorreo(request.getCorreo());
        if (existente.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body(Map.of("mensaje", "La cuenta ya existe"));
        }
        Usuario nuevo = usuarioRepository.save(request);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario creado", "usuario", nuevo));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(Map.of("mensaje", "Backend conectado"));
    }
}
