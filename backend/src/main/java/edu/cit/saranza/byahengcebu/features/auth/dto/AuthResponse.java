package edu.cit.saranza.byahengcebu.features.auth.dto;

public class AuthResponse {

    private boolean success;
    private String message;

    private String fullname;
    private String email;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(
            boolean success,
            String message,
            String fullname,
            String email,
            String role
    ) {
        this.success = success;
        this.message = message;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
    }

    public AuthResponse(
            boolean success,
            String message
    ) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}