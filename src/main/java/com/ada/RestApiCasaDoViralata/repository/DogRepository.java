package com.ada.RestApiCasaDoViralata.repository;

import com.ada.RestApiCasaDoViralata.model.Dog;
import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository <Dog, Integer>{

    @Override
    Page<Dog> findAll(Pageable pageable);

    Dog findByName(String name);

    Dog findDogById (Integer id);

    Optional<Dog> findByColor(String color);

    Optional<Dog> findByGender(AnimalGender gender);

    Optional<Dog> findBySize(DogSize size);
}
