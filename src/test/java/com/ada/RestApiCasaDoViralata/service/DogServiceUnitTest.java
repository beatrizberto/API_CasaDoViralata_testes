package com.ada.RestApiCasaDoViralata.service;

import com.ada.RestApiCasaDoViralata.controller.dto.DogRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.DogResponse;
import com.ada.RestApiCasaDoViralata.model.Dog;
import com.ada.RestApiCasaDoViralata.repository.DogRepository;
import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogConvert;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class DogServiceUnitTest {

    private DogRequest dogRequest;
    private Dog dog;

    @InjectMocks
    private DogService dogService;

    @Mock
    private DogRepository dogRepository;


    @Test
    public void shouldReturnDogResponseWithSameAtributes_whenDogIsCreated() {
        dogRequest = new DogRequest();
        dogRequest.setName("DogName");
        dogRequest.setAge(3);
        dogRequest.setColor("Black");
        dogRequest.setGender(AnimalGender.MALE);
        dogRequest.setSize(DogSize.GRANDE);

        Dog dogEntity = DogConvert.toEntity(dogRequest);
        Mockito.when(dogRepository.save(any())).thenReturn(dogEntity);

        DogResponse dogResponse = dogService.createDog(dogRequest);
        Assertions.assertNotNull(dogResponse);

        Assertions.assertEquals(dogEntity.getId(), dogResponse.getId());
        Assertions.assertEquals("DogName", dogResponse.getName());
        Assertions.assertEquals(3, dogResponse.getAge());
        Assertions.assertEquals("Black", dogResponse.getColor());
        Assertions.assertEquals(AnimalGender.MALE, dogResponse.getGender());
        Assertions.assertEquals(DogSize.GRANDE, dogResponse.getSize());
    }

    @Test
    public void shouldThrowException_whenDogNameIsNullorEmpty(){
        DogRequest dogRequestEmptyName = new DogRequest();
        dogRequestEmptyName.setName("");

        DogRequest dogRequestNullName = new DogRequest();
        dogRequestNullName.setName(null);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dogService.createDog(dogRequestEmptyName),
                "Should throw exception for empty name");

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> dogService.createDog(dogRequestNullName),
                "Should throw exception for null name");

        Mockito.verify(dogRepository, Mockito.never()).save(Mockito.any());
    }



    @Test
    void getDogs() {


    }

    @Test
    public void shouldReturnNull_whenIdDoesNotExist() {
        Mockito.when(dogRepository.findDogById(Mockito.anyInt())).thenReturn(null);

        int mockId = 1234;

        DogResponse found = dogService.getDogById(mockId);

        Assertions.assertNull(found);

        }


    @Test
    void getDogByName() {
    }

    @Test
    void getDogByColor() {
    }

    @Test
    void getDogByGender() {
    }

    @Test
    void getDogBySize() {
    }

    @Test
    public void shouldKeepId_whenUpdated() {




    }

    @Test
    void deleteDog() {
    }
}