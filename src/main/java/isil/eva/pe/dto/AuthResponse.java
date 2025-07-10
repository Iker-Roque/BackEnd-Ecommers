package isil.eva.pe.dto;

import isil.eva.pe.jpa.modelo.User;

public class AuthResponse {
    private boolean success;
    private String message;
    private User user;
    
    // Constructores
    public AuthResponse() {}
    
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public AuthResponse(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }
    
    // Getters y Setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
