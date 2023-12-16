package com.ada.RestApiCasaDoViralata.utils;

import com.ada.RestApiCasaDoViralata.controller.dto.DogRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.DogResponse;
import com.ada.RestApiCasaDoViralata.model.Dog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class DogConvert {

    public static Dog toEntity(DogRequest dogRequest) {
        Dog dog = new Dog();
        dog.setName(dogRequest.getName());
        dog.setAge(dogRequest.getAge());
        dog.setColor(dogRequest.getColor());
        dog.setGender(dogRequest.getGender());
        dog.setSize(dogRequest.getSize());

        return dog;
    }

    public static DogResponse toResponse(Dog dog) {
        DogResponse dogResponse = new DogResponse();
        dogResponse.setId(dog.getId());
        dogResponse.setName(dog.getName());
        dogResponse.setAge(dog.getAge());
        dogResponse.setColor(dog.getColor());
        dogResponse.setGender(dog.getGender());
        dogResponse.setSize(dog.getSize());

        return dogResponse;
    }

    public static Page<DogResponse> toResponseList(Page<Dog> dogs) {
        List<DogResponse> dogResponses = new ArrayList<>();
        for (Dog dog : dogs) {
            DogResponse dogResponse = DogConvert.toResponse(dog);
            dogResponses.add(dogResponse);
        }

        //TODO: tentar fazer esse processo com stream

        return new PageImpl<>(dogResponses);

    }
}


