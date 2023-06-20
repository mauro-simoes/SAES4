package com.apisae.api.controllers.user;

import com.apisae.api.models.user.UserDTO;
import com.apisae.api.services.user.IServiceUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private IServiceUser serviceUser;

    @InjectMocks
    private UserController userController;

    @Test
    @DisplayName("Test not found quand l'utilisateur n'existe pas")
    void getUser_ShouldReturnNotFoundWhenUserDoesntExist() {
        ResponseEntity<UserDTO> expected = ResponseEntity.notFound().build();

        Mockito.doThrow(new RuntimeException()).when(serviceUser).getUserDTO();

        ResponseEntity<UserDTO> actual = userController.getUser();

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Test OK quand l'utilisateur existe")
    void getUser_ShouldUserDTO() {
        UserDTO userDTO = new UserDTO(
                "test@saes4.com",
                "nom",
                "prenom",
                "Paris",
                new Date(System.currentTimeMillis())
        );

        ResponseEntity<UserDTO> expected = ResponseEntity.ok().body(userDTO);

        Mockito.doReturn(userDTO).when(serviceUser).getUserDTO();

        ResponseEntity<UserDTO> actual = userController.getUser();

        assertEquals(expected,actual);
    }
}