package edu.cit.saranza.byahengcebu.controller;

import edu.cit.saranza.byahengcebu.model.User;
import edu.cit.saranza.byahengcebu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    UserRepository repo;

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (user.getFullname() == null || user.getFullname().trim().isEmpty()) {
            return "Full name is required";
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "Email is required";
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return "Password is required";
        }

        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists";
        }

        repo.save(user);

        return "Registration Successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "Email is required";
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return "Password is required";
        }

        User existing = repo.findByEmail(user.getEmail()).orElse(null);

        if (existing == null) {
            return "User not found";
        }

        if (!existing.getPassword().equals(user.getPassword())) {
            return "Wrong password";
        }

        return "Login Successful";
    }
}