package com.ada.RestApiCasaDoViralata.service;

import com.ada.RestApiCasaDoViralata.controller.dto.DogRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.DogResponse;
import com.ada.RestApiCasaDoViralata.model.Dog;
import com.ada.RestApiCasaDoViralata.repository.DogRepository;
import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogConvert;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    @Autowired
    DogRepository dogRepository;

    public DogResponse createDog(DogRequest dogRequest) {
        Dog dogEntity = dogRepository.save(DogConvert.toEntity(dogRequest));
        return DogConvert.toResponse(dogEntity);
    }

    public Page<DogResponse> getDogs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size); //Sort.Direction.ASC, "name"
        Page<Dog> dogs = dogRepository.findAll(pageRequest);
        return DogConvert.toResponseList(dogs);

    }

    public DogResponse getDogById(Integer id) {
        return DogConvert.toResponse(dogRepository.findById(id).get());
    }

    public DogResponse getDogByName(String name) {
        return DogConvert.toResponse(dogRepository.findByName(name));
    }

    public DogResponse getDogByColor(String color) {
        return DogConvert.toResponse(dogRepository.findByColor(color).get());
    }

    public DogResponse getDogByGender(AnimalGender gender) {
        return DogConvert.toResponse(dogRepository.findByGender(gender).get());
    }

    public DogResponse getDogBySize(DogSize size) {
        return DogConvert.toResponse(dogRepository.findBySize(size).get());
    }


    public DogResponse updateDog(Integer id, DogRequest dogRequest) {
        Dog dog = DogConvert.toEntity(dogRequest);
        dog.setId(id);
        return DogConvert.toResponse(dogRepository.save(dog));
    }

    public void deleteDog (Integer id){
        dogRepository.deleteById(id);

    }
}
