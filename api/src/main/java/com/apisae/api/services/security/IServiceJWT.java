package com.apisae.api.services.security;


import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Mauro Simoes
 */

public interface IServiceJWT {

    /**
     * Genere un jeton JWT en fonction de l'utilisateur
     *
     * @param userDetails l'utilisateur
     * @return le jeton JWT
     */
    String generateToken(UserDetails userDetails);

    /**
     * Extrait le mail contenu dans un jeton JWT
     *
     * @param jwtToken le jeton
     * @return le mail utilisateur
     */
    String extractUsername(String jwtToken);

    /**
     * Verifie si un jeton JWT est valide (pas expire et appartient a l'utilisateur)
     *
     * @param jwtToken le jeton
     * @param userDetails l'utilisateur
     * @return true si le jeton est valide, false sinon
     */
    boolean tokenIsValid(String jwtToken, UserDetails userDetails);
}
