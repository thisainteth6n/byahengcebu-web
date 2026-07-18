package edu.cit.saranza.byahengcebu.features.auth.controller;

import edu.cit.saranza.byahengcebu.features.auth.entity.User;
import edu.cit.saranza.byahengcebu.features.auth.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }

    // ==========================
    // GET ALL DRIVERS
    // ==========================

    @GetMapping("/drivers")
    public List<User> getDrivers() {

        return userService.getDrivers();

    }

}