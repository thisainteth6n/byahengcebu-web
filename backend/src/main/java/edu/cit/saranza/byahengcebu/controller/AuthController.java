package edu.cit.saranza.byahengcebu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.saranza.byahengcebu.model.User;
import edu.cit.saranza.byahengcebu.repository.UserRepository;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {


    @Autowired
    UserRepository repo;



    @PostMapping("/register")
    public String register(
            @RequestBody User user
    ){

        repo.save(user);

        return "Registration Successful";

    }



    @PostMapping("/login")
    public String login(
            @RequestBody User user
    ){


        User existing =
                repo.findByEmail(user.getEmail())
                        .orElse(null);


        if(existing==null)
            return "User not found";


        if(!existing.getPassword()
                .equals(user.getPassword()))

            return "Wrong password";


        return "Login Successful";


    }


}