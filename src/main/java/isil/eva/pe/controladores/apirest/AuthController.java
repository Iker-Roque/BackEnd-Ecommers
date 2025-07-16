package isil.eva.pe.controladores.apirest;

import isil.eva.pe.dto.*;
import isil.eva.pe.jpa.modelo.*;
import isil.eva.pe.jpa.repositorios.UsuarioRepository;
import isil.eva.pe.jpa.repositorios.AdminRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AdminRepository adminRepository;

    // ======= Usuario ========

    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody LoginRequest request) {
        Optional<Usuario> userOpt = usuarioRepository.findByCorreoAndContraseña(request.getCorreo(), request.getContraseña());
        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensaje", "Credenciales inválidas"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUsuario(@RequestBody Usuario nuevoUsuario) {
        if (nuevoUsuario.getNombre() == null || nuevoUsuario.getNombre().isBlank() ||
            nuevoUsuario.getCorreo() == null || nuevoUsuario.getCorreo().isBlank() ||
            nuevoUsuario.getContraseña() == null || nuevoUsuario.getContraseña().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Nombre, correo y contraseña son obligatorios"));
        }
        if (usuarioRepository.findByCorreo(nuevoUsuario.getCorreo()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensaje", "La cuenta ya existe"));
        }
        Usuario saved = usuarioRepository.save(nuevoUsuario);
        return ResponseEntity.ok(Map.of("mensaje", "Usuario creado", "usuario", saved));
    }

    // ======= Administrador ========

    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAdmin(@RequestBody AuthRequest request) {
        Optional<Admin_> adminOpt = adminRepository.findByCorreo(request.getCorreo());
        if (adminOpt.isPresent()) {
            Admin_ admin = adminOpt.get();
            // Solo validamos que coincida la contraseña sin encriptar
            if (admin.getContraseña().equals(request.getContraseña())) {
                return ResponseEntity.ok(admin);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("mensaje", "Credenciales incorrectas"));
    }
}
