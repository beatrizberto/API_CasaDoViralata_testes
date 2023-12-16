package com.ada.RestApiCasaDoViralata.service;

import com.ada.RestApiCasaDoViralata.controller.dto.UserRequest;
import com.ada.RestApiCasaDoViralata.controller.dto.UserResponse;
import com.ada.RestApiCasaDoViralata.model.User;
import com.ada.RestApiCasaDoViralata.repository.UserRepository;
import com.ada.RestApiCasaDoViralata.utils.UserConvert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

@ExtendWith(SpringExtension.class)
public class UserServiceUnitTest {

    private UserRequest userRequest;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        userRequest = new UserRequest(
                "Name",
                "name@email.com",
                "password");

        Mockito.when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

    }

    @Test
    public void usuario_criado_deve_ter_nome_email() {
        User user = UserConvert.toEntity(userRequest);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserResponse userResponse = userService.createUser(userRequest);

        Assertions.assertEquals("Name", userResponse.getName());
        Assertions.assertEquals("name@email.com", userResponse.getEmail());
       // Mockito.verify(passwordEncoder).encode("password");
    }

    @Test
    public void tentar_criar_usuario_com_email_existente_deve_lancar_excecao() {
        Mockito.when(userRepository.findByEmail("name@email.com"))
                .thenReturn(new User());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.createUser(userRequest));

        Mockito.verify(userRepository, never()).save(any());
    }

    @Test
    public void obter_lista_de_usuarios() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "User1", "user1@email.com", "encodedPassword", true));
        userList.add(new User(2, "User2", "user2@email.com", "encodedPassword", true));

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<UserResponse> userResponses = userService.getUsers();

        Assertions.assertNotNull(userResponses);
        Assertions.assertEquals(2, userResponses.size());
        Assertions.assertEquals("User1", userResponses.get(0).getName());
        Assertions.assertEquals("user1@email.com", userResponses.get(0).getEmail());
        Assertions.assertEquals("User2", userResponses.get(1).getName());
        Assertions.assertEquals("user2@email.com", userResponses.get(1).getEmail());

    }

    @Test
    public void buscar_usuario_por_id() {
        User user = new User(1, "User1", "user1@email.com", "encodedPassword", true);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));

        UserResponse userResponse = userService.getUserById(1);

        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals("User1", userResponse.getName());
        Assertions.assertEquals("user1@email.com", userResponse.getEmail());

    }

    @Test
    public void buscar_usuario_por_id_inexistente_retorna_excecao() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.getUserById(1));


    }

    @Test
    public void obter_usuario_por_nome_existente() {
        User user = new User(1, "User1", "user1@email.com", "encodedPassword", true);
        Mockito.when(userRepository.findByName("User1")).thenReturn(Optional.of(user));

        UserResponse userResponse = userService.getUserByName("User1");

        Assertions.assertNotNull(userResponse);
        Assertions.assertEquals("User1", userResponse.getName());
        Assertions.assertEquals("user1@email.com", userResponse.getEmail());
    }

    @Test
    public void obter_usuario_por_nome_inexistente_retora_excecao() {
        Mockito.when(userRepository.findByName("User1")).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> userService.getUserByName("User1"));


    }

    @Test
    public void atualizar_usuario_com_sucesso() {
        // Criação do objeto UserRequest
        UserRequest userRequest = new UserRequest("Name", "name@email.com", "password");

        // Configuração do comportamento do mock do repositório
        Mockito.when(userRepository.save(any())).thenReturn(UserConvert.toEntity(userRequest));

        // Chamada do método a ser testado
        UserResponse userResponse = userService.updateUser(1, userRequest);

        // Verificações
        if (userResponse != null) {
            Assertions.assertEquals("Name", userResponse.getName());
            Assertions.assertEquals("name@email.com", userResponse.getEmail());
        } else {
            Assertions.fail("O método updateUser retornou null");
        }

        Mockito.verify(userRepository).save(any());
    }



    @Test
    public void deletar_usuario_desativa_atributo_active() {
        // Dado um usuário existente com ID 1 e active=true
        User existingUser = new User(1, "User1", "user1@email.com", "encodedPassword", true);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));

        // Quando o método deleteUser é chamado
        userService.deleteUser(1);

        // Então o método findById é chamado para obter o usuário existente
        Mockito.verify(userRepository).findById(1);

        // E o atributo active do usuário é configurado como false
        Assertions.assertFalse(existingUser.isActive());

        // E o método save do repositório é chamado para salvar as alterações
        Mockito.verify(userRepository).save(existingUser);
    }

    @Test
    public void deletar_usuario_que_nao_existe_dispara_excecao() {
        // Dado um usuário com ID 1 que não existe no repositório
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());

        // Quando o método deleteUser é chamado para um ID que não existe
        Assertions.assertThrows(NoSuchElementException.class, () -> userService.deleteUser(1));

        // Então o método findById é chamado para obter o usuário (que não existe)
        Mockito.verify(userRepository).findById(1);

        // E o método save do repositório não deve ser chamado, pois o usuário não existe
        Mockito.verify(userRepository, never()).save(any(User.class));
    }


}


