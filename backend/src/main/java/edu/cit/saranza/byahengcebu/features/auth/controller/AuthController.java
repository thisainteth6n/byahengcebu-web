package edu.cit.saranza.byahengcebu.features.auth.controller;

import edu.cit.saranza.byahengcebu.features.auth.dto.AuthResponse;
import edu.cit.saranza.byahengcebu.features.auth.dto.LoginRequest;
import edu.cit.saranza.byahengcebu.features.auth.dto.RegisterRequest;
import edu.cit.saranza.byahengcebu.features.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

}