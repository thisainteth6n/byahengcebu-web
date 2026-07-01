package edu.cit.saranza.byahengcebu.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.cit.saranza.byahengcebu.model.User;


public interface UserRepository
        extends JpaRepository<User,Long>{


    Optional<User> findByEmail(String email);


}