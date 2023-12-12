package com.ada.RestApiCasaDoViralata.controller.dto;

import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class DogResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String color;
    private AnimalGender gender;
    private DogSize size;




}
