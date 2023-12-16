package com.ada.RestApiCasaDoViralata.controller.dto;
import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DogRequest {
    private String name;
    private Integer age;
    private String color;
    private AnimalGender gender;
    private DogSize size;

}


