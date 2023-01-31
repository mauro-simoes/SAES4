package com.apisae.api.models.user;


import com.apisae.api.models.choix.Choix;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nom",nullable = false)
    private String nom;

    @Column(name = "prenom",nullable = false)
    private String prenom;

    @Column(name = "ville",nullable = false)
    private String ville;

    @Column(name = "date_de_naissance",nullable = false)
    private Date dateNaissance;

    @OneToMany(mappedBy = "utilisateur")
    private List<Choix> choix;

}
