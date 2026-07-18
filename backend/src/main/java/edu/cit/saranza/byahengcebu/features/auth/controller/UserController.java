package edu.cit.saranza.byahengcebu.features.auth.controller;

import edu.cit.saranza.byahengcebu.features.auth.entity.User;
import edu.cit.saranza.byahengcebu.features.auth.repository.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/drivers")
    public List<User> getDrivers() {

        return userRepository.findByRole("DRIVER");

    }

}