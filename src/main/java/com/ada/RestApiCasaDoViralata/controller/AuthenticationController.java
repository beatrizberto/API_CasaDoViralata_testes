package com.ada.RestApiCasaDoViralata.controller;

import com.ada.RestApiCasaDoViralata.Infra.security.TokenService;
import com.ada.RestApiCasaDoViralata.controller.dto.LoginRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.TokenResponse;
import com.ada.RestApiCasaDoViralata.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {

        var authenticate = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        var authentication = authenticationManager.authenticate(authenticate);
        var token = tokenService.tokenGenerate((User) authentication.getPrincipal());


        return ResponseEntity.ok().body(new TokenResponse(token));
    }
}
