package com.apisae.api.models.authentification;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequeteCreationCompte {
    private String email;
    private String nom;
    private String prenom;
    private String password;
    private String ville;
    private Date dateNaissance;
}