package com.apisae.api.models.user;

import java.util.Date;

public record UserDTO(
        String email,
        String nom,
        String prenom,
        String ville,
        Date dateNaissance
) {
}
