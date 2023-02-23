package com.apisae.api.models.reponse;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.user.User;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReponseId implements Serializable {
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