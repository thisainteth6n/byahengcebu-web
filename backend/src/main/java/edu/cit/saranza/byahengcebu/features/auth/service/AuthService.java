package edu.cit.saranza.byahengcebu.features.auth.service;

import edu.cit.saranza.byahengcebu.features.auth.dto.AuthResponse;
import edu.cit.saranza.byahengcebu.features.auth.dto.LoginRequest;
import edu.cit.saranza.byahengcebu.features.auth.dto.RegisterRequest;
import edu.cit.saranza.byahengcebu.features.auth.entity.User;
import edu.cit.saranza.byahengcebu.features.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {

        if (request.getFullname() == null || request.getFullname().trim().isEmpty()) {
            return new AuthResponse(false, "Full name is required");
        }

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return new AuthResponse(false, "Email is required");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return new AuthResponse(false, "Password is required");
        }

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new AuthResponse(false, "Email already exists");
        }

        User user = new User();

        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        userRepository.save(user);

        return new AuthResponse(
                true,
                "Registration Successful",
                user.getFullname(),
                user.getEmail(),
                user.getRole()
        );
    }

    public AuthResponse login(LoginRequest request) {

        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            return new AuthResponse(false, "Email is required");
        }

        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return new AuthResponse(false, "Password is required");
        }

        User existing = userRepository.findByEmail(request.getEmail()).orElse(null);

        if (existing == null) {
            return new AuthResponse(false, "User not found");
        }

        if (!existing.getPassword().equals(request.getPassword())) {
            return new AuthResponse(false, "Wrong password");
        }

        return new AuthResponse(
                true,
                "Login Successful",
                existing.getFullname(),
                existing.getEmail(),
                existing.getRole()
        );
    }

}