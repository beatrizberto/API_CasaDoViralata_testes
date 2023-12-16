package com.ada.RestApiCasaDoViralata.controller;

import com.ada.RestApiCasaDoViralata.controller.dto.DogRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.DogResponse;
import com.ada.RestApiCasaDoViralata.service.DogService;
import com.ada.RestApiCasaDoViralata.utils.AnimalGender;
import com.ada.RestApiCasaDoViralata.utils.DogSize;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class DogControllerIntegrationTest {

    @MockBean
    private DogService dogService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateDogSuccessfully() throws Exception {
        DogRequest dogRequest = new DogRequest("DogName", 1, "black", AnimalGender.MALE, DogSize.BIG);

        DogResponse mockDogResponse = new DogResponse();
        mockDogResponse.setId(1);
        mockDogResponse.setName("DogName");
        mockDogResponse.setAge(1);
        mockDogResponse.setColor("black");
        mockDogResponse.setGender(AnimalGender.MALE);
        mockDogResponse.setSize(DogSize.BIG);

        Mockito.when(dogService.createDog(Mockito.any(DogRequest.class))).thenReturn(mockDogResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/dog")
                                .content(objectMapper.writeValueAsString(dogRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("DogName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.color").value("black"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender").value("MALE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size").value("BIG"));
    }


    @Test
    public void shouldNotCreateDog_whenNameIsNull() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/dog")
                        .content("""
                                {
                                    "name": "",
                                    "age": "1",
                                    "color" : "black",
                                    "gender": MALE,
                                    "size": "BIG"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().isBadRequest()
        );
    }

    @Test
    public void getAllDogs() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/dog"))
                .andDo(
                        MockMvcResultHandlers.print())
                .andExpect(
                        MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldUpdateDog_givenExistingId() throws Exception {
        int existingDogId = 1;

        mockMvc.perform(MockMvcRequestBuilders.put("/dog/{id}", existingDogId)
                        .content("""
                                {
                                    "name": "NewName",
                                    "age": 2,
                                    "color": "White",
                                    "gender": "FEMALE",
                                    "size": "MEDIUM"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void deleteDog() throws Exception {
        int existingDogId = 123;

        mockMvc.perform(MockMvcRequestBuilders.delete("/dog/{id}", existingDogId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
