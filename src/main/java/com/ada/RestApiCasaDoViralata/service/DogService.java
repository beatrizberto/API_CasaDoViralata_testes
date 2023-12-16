package com.ada.RestApiCasaDoViralata.service;

import com.ada.RestApiCasaDoViralata.controller.dto.DogRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.DogResponse;
import com.ada.RestApiCasaDoViralata.model.Dog;
import com.ada.RestApiCasaDoViralata.repository.DogRepository;
import com.ada.RestApiCasaDoViralata.utils.DogConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    @Autowired
    DogRepository dogRepository;

    public DogResponse createDog(DogRequest dogRequest) {

        String dogName = dogRequest.getName();
        if (dogName == null || dogName.isEmpty()) {
            throw new IllegalArgumentException("Name não pode ser nulo ou vazio");
        }

        Dog dogEntity = dogRepository.save(DogConvert.toEntity(dogRequest));
        return DogConvert.toResponse(dogEntity);
    }

    public Page<DogResponse> getDogs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size); //Sort.Direction.ASC, "name"
        Page<Dog> dogs = dogRepository.findAll(pageRequest);
        return DogConvert.toResponseList(dogs);

    }

    public DogResponse getDogById(Integer id) {
        Dog dog = dogRepository.findDogById(id);
        return dog != null ? DogConvert.toResponse(dog) : null;

    }

    public DogResponse updateDog(Integer id, DogRequest dogRequest) {
        Dog existingDog = dogRepository.findDogById(id);
        if (existingDog == null) {
            throw new IllegalArgumentException ("Dog not found for ID: " + id);
        } else {
            existingDog = DogConvert.toEntity(dogRequest);
            existingDog.setId(id);
           }
        return DogConvert.toResponse(dogRepository.save(existingDog));

    }

    public void deleteDog (Integer id){
        dogRepository.deleteById(id);

    }
}
