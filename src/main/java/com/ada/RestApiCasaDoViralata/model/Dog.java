package com.ada.RestApiCasaDoViralata.model;

import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import jakarta.persistence.*;
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

    private String name;

    private Integer age;

    private String color;

    @Column(name = "Entrada")
    private Date admissionDate;

    @Column(nullable = false)
    private AnimalGender gender;

    @Column(name = "porte")
    private DogSize size;

    public Dog(String name, String color, AnimalGender gender) {
        this.name = name;
        this.color = color;
        this.gender = gender;
    }
}


