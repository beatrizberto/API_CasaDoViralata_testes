package com.ada.RestApiCasaDoViralata.controller;

import com.ada.RestApiCasaDoViralata.controller.dto.DogRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.DogResponse;
import com.ada.RestApiCasaDoViralata.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/dog")
public class DogController {

    @Autowired
    DogService dogService;

    @GetMapping
    public ResponseEntity<Page<DogResponse>> getAllDogs(
            @RequestParam(
                    value = "page",
                    required = false,
                    defaultValue = "0"
            ) int page,
            @RequestParam(
                    value = "size",
                    required = false,
                    defaultValue = "5"

            ) int size
    ) {
        return ResponseEntity.ok(dogService.getDogs(page, size));
    }

    @PostMapping
    public ResponseEntity<DogResponse> createDog(@RequestBody DogRequest dogRequest) {
        DogResponse dog = dogService.createDog(dogRequest);
        return ResponseEntity.created(URI.create("/dog/" + dog.getId())).body(dog);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DogResponse> updateDog(
            @PathVariable Integer id,
            @RequestBody DogRequest dogRequest
    ){

        return ResponseEntity.ok(dogService.updateDog(id, dogRequest));

    }


    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable Integer id) {
        dogService.deleteDog(id);
    }

//
//    @GetMapping
//    public List<DogResponse> getDog(
//            @RequestParam(name = "dogId", required = false) Integer id,
//            @RequestParam (name = "dogName", required = false) String name,
//            @RequestParam (name = "dogColor", required = false) String color,
//            @RequestParam (name = "dogGender", required = false) AnimalGender gender,
//            @RequestParam (name = "dogSize", required = false) DogSize size
//
//            ){
//        return dogService.getDogs();
//    }


}
