package com.ada.RestApiCasaDoViralata.controller.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class LoginRequest {

    @Email
    private String email;
    private Integer password;
}
