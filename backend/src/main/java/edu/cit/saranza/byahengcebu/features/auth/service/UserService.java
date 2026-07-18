package edu.cit.saranza.byahengcebu.features.auth.service;

import edu.cit.saranza.byahengcebu.features.auth.entity.User;
import edu.cit.saranza.byahengcebu.features.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ==========================
    // GET ALL DRIVERS
    // ==========================

    public List<User> getDrivers() {

        return userRepository.findByRole("DRIVER");

    }

}