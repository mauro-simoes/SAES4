package com.apisae.api.models.choix;

import com.apisae.api.models.aliment.Aliment;
import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "choix")
public class Choix {
    @Id
    @ManyToOne
    @JoinColumn(name = "utilisateur")
    @MapsId("utilisateur")
    private User utilisateur;

    @Id
    @ManyToOne
    @JoinColumn(name = "sondage")
    @MapsId("sondage")
    private Sondage sondage;

    @Id
    @ManyToOne
    @JoinColumn(name = "aliment")
    @MapsId("aliment")
    private Aliment aliment;

}
