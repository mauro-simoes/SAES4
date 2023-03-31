package com.apisae.api.models.reponsepossible;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reponse_aliment")
public class ReponseAliment extends ReponsePossible{

    @ManyToOne
    @JoinColumn(name="aliment", nullable = false)
    private Aliment aliment;

    @Override
    public String getValue() {
        return aliment.getAlim_nom_fr();
    }
}
