/**
 * @author Mauro Simoes
 */

package com.apisae.api.services.authentification;

import com.apisae.api.exceptions.NotUniqueUserEx;
import com.apisae.api.exceptions.RequeteInvalideEx;
import com.apisae.api.models.authentification.ReponseAuth;
import com.apisae.api.models.authentification.RequeteAuth;
import com.apisae.api.models.authentification.RequeteCreationCompte;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;

public interface IServiceAuthentification {

    /**
     * Cree un compte utilisateur
     *
     * @param requete la requete de creation de compte
     * @return le jeton d'access de l'utilisateur
     * @throws NotUniqueUserEx si le mail est deja donne est deja associe a un compte
     * @throws RequeteInvalideEx si les champs ne respectent pas les criteres
     */
    ReponseAuth creerCompte(@NonNull RequeteCreationCompte requete);

    /**
     * Authentifie un utilisateur
     *
     * @param requete la requete d'authentification
     * @return le jeton d'access de l'utilisateur
     * @throws BadCredentialsException si le mail ou mot de passe sont incorrects
     * @throws RequeteInvalideEx si les champs ne respectent pas les criteres
     */
    ReponseAuth authentifier(@NonNull RequeteAuth requete);

}
