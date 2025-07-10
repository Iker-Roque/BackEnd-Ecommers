package isil.eva.pe.servicios;

import isil.eva.pe.dto.AuthResponse;
import isil.eva.pe.jpa.modelo.User;
import isil.eva.pe.jpa.repositorios.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public AuthResponse login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isEmpty()) {
            return new AuthResponse(false, "Usuario no encontrado en la base de datos");
        }
        
        User user = userOpt.get();
        
        // Verificar contraseña
        if (passwordEncoder.matches(password, user.getPassword())) {
            // No devolver la contraseña en la respuesta
            user.setPassword(null);
            return new AuthResponse(true, "Login exitoso", user);
        } else {
            return new AuthResponse(false, "Contraseña incorrecta");
        }
    }
    
    public AuthResponse register(String name, String email, String password) {
        // Verificar si el usuario ya existe
        if (userRepository.existsByEmail(email)) {
            return new AuthResponse(false, "Este email ya está registrado");
        }
        
        // Encriptar contraseña
        String hashedPassword = passwordEncoder.encode(password);
        
        // Crear nuevo usuario
        User newUser = new User(name, email, hashedPassword);
        User savedUser = userRepository.save(newUser);
        
        // No devolver la contraseña en la respuesta
        savedUser.setPassword(null);
        return new AuthResponse(true, "Usuario registrado exitosamente", savedUser);
    }
}