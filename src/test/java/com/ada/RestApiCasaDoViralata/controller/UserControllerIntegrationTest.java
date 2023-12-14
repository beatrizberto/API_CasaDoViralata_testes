package com.ada.RestApiCasaDoViralata.controller;

import com.ada.RestApiCasaDoViralata.controller.dto.UserRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.UserResponse;
import com.ada.RestApiCasaDoViralata.service.UserService;
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
class UserControllerIntegrationTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateUserSuccessfully() throws Exception {
        UserRequest userRequest = new UserRequest(
                "Name",
                "name@email.com",
                "password"
        );

        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setId(1234);
        mockUserResponse.setName("Name");
        mockUserResponse.setEmail("name@email.com");

        Mockito.when(userService.createUser(Mockito.any(UserRequest.class))).thenReturn(mockUserResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/user")
                                .content(objectMapper.writeValueAsString(userRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1234))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("name@email.com"));

    }

    @Test
    void getUserById() throws Exception {
        int existingUserId = 1234;

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/user/{id}", existingUserId))
                .andDo(
                        MockMvcResultHandlers.print())
                .andExpect(
                        MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void shouldUpdateUser_givenExistingId() throws Exception {
        int existingUserId = 1234;

        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}", existingUserId)
                        .content("""
                                {
                                    "name": "NewName",
                                    "email": "newname@email.com",
                                    "password": "password"
                                                                    }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void deleteUser() throws Exception {
        int existingUserId = 123;
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{id}", existingUserId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}