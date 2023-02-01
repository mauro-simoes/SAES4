package com.apisae.api.models.reponse;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "reponse")
public class Reponse {

    @Id
    @ManyToOne
    @JoinColumn(name = "utilisateur")
    @MapsId("utilisateur")
    private User utilisateur;

    @Id
    @ManyToOne
    @JoinColumn(name = "reponse")
    @MapsId("reponse")
    private ReponsePossible reponse;

}
