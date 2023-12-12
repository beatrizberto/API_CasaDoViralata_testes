package com.ada.RestApiCasaDoViralata.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "O campo 'Name' não pode estar vazio.")
    @Length(min = 3, max = 35)
    private String name;

    @NotBlank(message = "O campo 'email' não pode estar vazio.")
    @Email
    private String email;

    private String password;

}
