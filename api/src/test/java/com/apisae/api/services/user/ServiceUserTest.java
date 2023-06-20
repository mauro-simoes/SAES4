package com.apisae.api.services.user;

import com.apisae.api.models.user.User;
import com.apisae.api.models.user.UserDTO;
import com.apisae.api.repositories.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ServiceUserTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserDTOMapper userDTOMapper;

    @InjectMocks
    ServiceUser serviceUser;

    @Test
    @DisplayName("loadUserByUsername : exception quand l'utilisateur n'existe pas")
    void loadUserByUsername_shouldReturnErrorWhenUserDoesntExist() {
        Mockito.doReturn(Optional.empty()).when(userRepository).findUserByEmailIs(Mockito.eq("inexistant@gmail.com"));
        assertThrows(UsernameNotFoundException.class, () -> serviceUser.loadUserByUsername("inexistant@gmail.com"));
    }

    @Test
    @DisplayName("loadUserByUsername : ok quand l'utilisateur existe")
    void loadUserByUsername_shouldReturnUser() {
        User expected = new User("test@saes4.com",
                "testNom",
                "testPrenom",
                "motDePasse",
                "Paris",
                new Date(System.currentTimeMillis()));

        Mockito.doReturn(Optional.of(expected)).when(userRepository).findUserByEmailIs(Mockito.eq("test@saes4.com"));

        UserDetails actual = serviceUser.loadUserByUsername("test@saes4.com");

        assertEquals(expected, actual);
    }

    @Nested
    class GetUserTests{

        private User utilisateur;

        private Authentication authentication;

        @BeforeEach
        void initBouchonSecurite(){
            // bouchon de securite
            authentication = Mockito.mock(Authentication.class);
            SecurityContext securityContext = Mockito.mock(SecurityContext.class);
            Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
            SecurityContextHolder.setContext(securityContext);
        }

        @Test
        @DisplayName("getUser : exception quand l'utilisateur n'existe pas")
        void getUser_ShouldReturnErrorWhemUserDoesntExist() {

            utilisateur = new User();
            utilisateur.setEmail("test@saes4.com");

            Mockito.doReturn(utilisateur).when(authentication).getPrincipal();
            Mockito.doReturn(Optional.empty()).when(userRepository).findUserByEmailIs("test@saes4.com");

            assertThrows(UsernameNotFoundException.class,() -> serviceUser.getUserDTO());
        }


        @Test
        @DisplayName("getUser : ok quand l'utilisateur existe")
        void getUser_ShouldReturnUserDTO() {
            Date date = new Date(System.currentTimeMillis());

            utilisateur = new User("test@saes4.com",
                    "testNom",
                    "testPrenom",
                    "motDePasse",
                    "Paris",
                    date);

            UserDTO expected = new UserDTO("test@saes4.com",
                    "testNom",
                    "testPrenom",
                    "Paris",
                    date);

            Mockito.doReturn(utilisateur).when(authentication).getPrincipal();
            Mockito.doReturn(Optional.of(utilisateur)).when(userRepository).findUserByEmailIs("test@saes4.com");
            Mockito.doReturn(expected).when(userDTOMapper).apply(Mockito.eq(utilisateur));

            UserDTO actual = serviceUser.getUserDTO();

            assertEquals(expected,actual);
        }

    }

}