package com.apisae.api.controllers.authentification;

import com.apisae.api.exceptions.NotUniqueUserEx;
import com.apisae.api.models.authentification.ReponseAuth;
import com.apisae.api.models.authentification.RequeteAuth;
import com.apisae.api.models.authentification.RequeteCreationCompte;
import com.apisae.api.models.error.ErrorBody;
import com.apisae.api.services.authentification.IServiceAuthentification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import java.sql.Date;
import java.util.concurrent.TimeUnit;


@ExtendWith(MockitoExtension.class)
class AuthentificationControllerTest {

    @Mock
    private IServiceAuthentification serviceAuthentification;

    @InjectMocks
    private AuthentificationController authentificationController;

    @Test
    @DisplayName("Test champs invalides pour creation de compte")
    void creerCompte_ShouldReturnBadRequestWhenFieldsAreInvalid() {
        ResponseEntity<Object> expected = ResponseEntity.badRequest().body(new ErrorBody("Les champs sont invalides"));

        RequeteCreationCompte requeteCreationCompte = new RequeteCreationCompte(" ", " ", " fsdf", "Efef", "         ",new Date(System.currentTimeMillis()));

        ResponseEntity<Object> actual = authentificationController.creerCompte(requeteCreationCompte);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Test compte deja existant pour creation de compte")
    void creerCompte_ShouldReturnInternalServerErrorWhenEmailIsTaken() {
        ResponseEntity<Object> expected = ResponseEntity.internalServerError().body(new ErrorBody("Il existe déjà un utilisateur avec ce mail"));

        RequeteCreationCompte requeteCreationCompte = new RequeteCreationCompte("test@saes4.com", "motDePasse", "testNom", "testPrenom", "Woippy",new Date(960508800000L));

        Mockito.doThrow(new NotUniqueUserEx("Il existe déjà un utilisateur avec ce mail"))
                .when(serviceAuthentification).creerCompte(ArgumentMatchers.any(RequeteCreationCompte.class));

        ResponseEntity<Object> actual = authentificationController.creerCompte(requeteCreationCompte);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }


    @Test
    @DisplayName("Test OK pour creation de compte")
    void creerCompte_ShouldReturnToken() {
        ResponseEntity<Object> expected = ResponseEntity.ok().body(new ReponseAuth("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JAZ21haWwuY29tIiwiaWF0IjoxNjc1ODYzODg5LCJleHAiOjE3MDczOTk4ODl9.AdyDDS-iZYnDQECzERpXI_fXIJftBgFcgY4p8FbVBf8"));

        RequeteCreationCompte requeteCreationCompte = new RequeteCreationCompte("test@saes4.com", "motDePasse", "testNom", "testPrenom", "Woippy",new Date(960508800000L));

        Mockito.doReturn(new ReponseAuth("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JAZ21haWwuY29tIiwiaWF0IjoxNjc1ODYzODg5LCJleHAiOjE3MDczOTk4ODl9.AdyDDS-iZYnDQECzERpXI_fXIJftBgFcgY4p8FbVBf8"))
                .when(serviceAuthentification).creerCompte(ArgumentMatchers.any(RequeteCreationCompte.class));

        ResponseEntity<Object> actual = authentificationController.creerCompte(requeteCreationCompte);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Test champs invalides pour authentification")
    void authenticate_ShouldReturnBadRequestWhenFieldsAreInvalid() {
        ResponseEntity<Object> expected = ResponseEntity.badRequest().body(new ErrorBody("Les champs sont invalides"));

        RequeteAuth requeteAuth = new RequeteAuth(" ", " brr");

        ResponseEntity<Object> actual = authentificationController.authenticate(requeteAuth);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Test infos incorrectes pour authentification")
    void authenticate_ShouldReturnInternalServerErrorWhenCredentialsAreIncorrect() {
        ResponseEntity<Object> expected = ResponseEntity.internalServerError().body(new ErrorBody("Email ou mot de passe incorrect"));

        RequeteAuth requeteAuth = new RequeteAuth("test@saes4.com", "motDePasse");

        Mockito.doThrow(new BadCredentialsException("Email ou mot de passe incorrect"))
                .when(serviceAuthentification).authentifier(ArgumentMatchers.any(RequeteAuth.class));

        ResponseEntity<Object> actual = authentificationController.authenticate(requeteAuth);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    @DisplayName("Test OK pour authentification")
    void authenticate_ShouldReturnTokenWhenOK() {
        ResponseEntity<Object> expected = ResponseEntity.ok().body(new ReponseAuth("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JAZ21haWwuY29tIiwiaWF0IjoxNjc1ODYzODg5LCJleHAiOjE3MDczOTk4ODl9.AdyDDS-iZYnDQECzERpXI_fXIJftBgFcgY4p8FbVBf8"));

        RequeteAuth requeteAuth = new RequeteAuth("test@saes4.com", "motDePasse");

        Mockito.doReturn(new ReponseAuth("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJib2JAZ21haWwuY29tIiwiaWF0IjoxNjc1ODYzODg5LCJleHAiOjE3MDczOTk4ODl9.AdyDDS-iZYnDQECzERpXI_fXIJftBgFcgY4p8FbVBf8"))
                .when(serviceAuthentification).authentifier(ArgumentMatchers.any(RequeteAuth.class));

        ResponseEntity<Object> actual = authentificationController.authenticate(requeteAuth);

        Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}