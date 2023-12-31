package com.ada.RestApiCasaDoViralata.controller;

import com.ada.RestApiCasaDoViralata.controller.dto.UserRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.UserResponse;
import com.ada.RestApiCasaDoViralata.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserResponse> getUsers() {

        return userService.getUsers();
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(
         @Valid  @RequestBody UserRequest userDTO) {

        UserResponse user = userService.createUser(userDTO);
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {


        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Integer id,
            @RequestBody UserRequest userRequest
    ){

        return ResponseEntity.ok(userService.updateUser(id, userRequest));

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);

    }

}
