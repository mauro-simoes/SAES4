package com.apisae.api.controllers.authentification;

import com.apisae.api.exceptions.NotUniqueUserEx;
import com.apisae.api.models.authentification.ReponseAuth;
import com.apisae.api.models.authentification.RequeteAuth;
import com.apisae.api.models.authentification.RequeteCreationCompte;
import com.apisae.api.services.authentification.IServiceAuthentifiaction;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/authentification")
public class AuthentificationController {

    private final IServiceAuthentifiaction serviceAuthentifiaction;


    @RequestMapping("/creer-compte")
    public ResponseEntity<Object> creerCompte(@NonNull @RequestBody RequeteCreationCompte requete){
        if(!requeteCreationValide(requete))
            return ResponseEntity.badRequest().body("Les champs sont invalides");
        ReponseAuth reponse;
        try{
            reponse = serviceAuthentifiaction.creerCompte(requete);
        }catch (NotUniqueUserEx e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok().body(reponse);
    }

    private boolean requeteCreationValide(@NonNull RequeteCreationCompte requete){
        return mailValide(requete.getEmail()) &&
                champValide(requete.getNom()) &&
                champValide(requete.getPrenom())  &&
                champValide(requete.getPassword())  &&
                requete.getPassword().length() >= 8 &&
                champValide(requete.getVille()) &&
                requete.getDateNaissance() != null;
    }

    @RequestMapping("/authentifier")
    public ResponseEntity<Object> authenticate(@NonNull @RequestBody RequeteAuth requete){
        if(!requeteAuthValide(requete))
            return ResponseEntity.badRequest().body("Les champs sont invalides");
        ReponseAuth reponse;
        try{
            reponse = serviceAuthentifiaction.authentifier(requete);
        }catch (BadCredentialsException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(reponse);
    }

    private boolean requeteAuthValide(@NonNull RequeteAuth requete){
        return mailValide(requete.getEmail()) &&
                champValide(requete.getPassword()) &&
                requete.getPassword().length() >= 8;
    }

    private boolean champValide(String champ){
        return champ != null && !champ.isBlank() && !champ.isEmpty();
    }

    private boolean mailValide(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email != null &&
                !email.isEmpty() &&
                !email.isBlank() &&
                Pattern.compile(regex)
                .matcher(email)
                .matches();
    }

}
