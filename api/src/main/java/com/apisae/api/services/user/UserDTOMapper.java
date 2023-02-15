package com.apisae.api.services.user;

import com.apisae.api.models.user.User;
import com.apisae.api.models.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Mauro Simoes
 */

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    /**
     * Transforme un objet utilisateur en utilisateur sans mot de passe.
     * <p>
     * Cet objet permet de transferer les donnees utilisateur au front
     *
     * @param user the function argument
     * @return objet utilisateur sans mot de passe
     */
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getEmail(),
                user.getNom(),
                user.getPrenom(),
                user.getVille(),
                user.getDateNaissance()
        );
    }
}
