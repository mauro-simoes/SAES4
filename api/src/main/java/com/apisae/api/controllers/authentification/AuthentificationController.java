package com.apisae.api.controllers.authentification;

import com.apisae.api.exceptions.NotUniqueUserEx;
import com.apisae.api.models.authentification.ReponseAuth;
import com.apisae.api.models.authentification.RequeteAuth;
import com.apisae.api.models.authentification.RequeteCreationCompte;
import com.apisae.api.models.error.ErrorBody;
import com.apisae.api.services.authentification.IServiceAuthentification;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mauro Simoes
 */

@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/authentification")
public class AuthentificationController {

    private final IServiceAuthentification serviceAuthentifiaction;

    /**
     * Cree un compte utilisateur
     *
     * @return le jeton d'access de l'utilisateur si le compte est cree avec success, un message d'erreur sinon
     */
    @PostMapping ("/creer-compte")
    public ResponseEntity<Object> creerCompte(@NonNull @RequestBody RequeteCreationCompte requete){
        if(!requete.estValide()){
            ErrorBody error = new ErrorBody("Les champs sont invalides");
            return ResponseEntity.badRequest().body(error);
        }
        ReponseAuth reponse;
        try{
            reponse = serviceAuthentifiaction.creerCompte(requete);
        }catch (NotUniqueUserEx e){
            ErrorBody error = new ErrorBody(e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
        return ResponseEntity.ok().body(reponse);
    }

    /**
     * Authentifie un utilisateur
     *
     * @return le jeton d'access de l'utilisateur si le compte est cree avec success, un message d'erreur sinon
     */
    @PostMapping("/authentifier")
    public ResponseEntity<Object> authenticate(@NonNull @RequestBody RequeteAuth requete){
        if(!requete.estValide()){
            ErrorBody error = new ErrorBody("Les champs sont invalides");
            return ResponseEntity.badRequest().body(error);
        }
        ReponseAuth reponse;
        try{
            reponse = serviceAuthentifiaction.authentifier(requete);
        }catch (BadCredentialsException e){
            ErrorBody error = new ErrorBody(e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
        return ResponseEntity.ok(reponse);
    }

}
