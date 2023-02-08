package com.apisae.api.services.user;

import com.apisae.api.models.user.User;
import com.apisae.api.models.user.UserDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {
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
