package isil.eva.pe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthRequest {
    @Email
    @NotBlank
    private String email;
    
    @NotBlank
    private String password;
    
    // Constructores
    public AuthRequest() {}
    
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}