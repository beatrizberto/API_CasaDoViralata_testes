package com.ada.RestApiCasaDoViralata.repository;

import com.ada.RestApiCasaDoViralata.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository <User, Integer> {

    @Override
    @Query(value = "SELECT * FROM USERS WHERE ACTIVE=TRUE", nativeQuery = true)
    List<User> findAll();

    Optional<User> findByName(String name);

    Integer findUserById (Integer id);


    UserDetails findByEmail(String email);
}
