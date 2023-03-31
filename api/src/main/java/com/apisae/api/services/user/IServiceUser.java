package com.apisae.api.services.user;

import com.apisae.api.models.user.User;
import com.apisae.api.models.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Mauro Simoes
 */

public interface IServiceUser extends UserDetailsService {

    /**
     * Recupere un utilisateur par son mail
     *
     * @param email le mail
     * @return l'utilisateur
     * @throws UsernameNotFoundException si l'utilisatuer n'existe pas
     */
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    /**
     * Recupere les informations de l'utilisateur sans les donnees sensibles
     *
     * @return les informations de l'utilisateur sans les donnees sensibles
     */
    UserDTO getUserDTO();

    User getCurrentUser();
}
