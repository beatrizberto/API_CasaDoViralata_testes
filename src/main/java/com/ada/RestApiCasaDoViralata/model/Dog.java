package com.ada.RestApiCasaDoViralata.model;

import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "dogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Column (nullable = false)
    private String name;

    private Integer age;

    private String color;

    @Column(name = "Sexo")
    private AnimalGender gender;

    @Column(name = "porte")
    private DogSize size;

//    public Dog(String name, String color, AnimalGender gender) {
//        this.name = name;
//        this.color = color;
//        this.gender = gender;
//    }
}


