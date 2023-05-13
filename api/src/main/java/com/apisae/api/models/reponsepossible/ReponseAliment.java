package com.apisae.api.models.reponsepossible;

import com.apisae.api.models.sondage.Question;
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

    public ReponseAliment(Question question,Aliment aliment) {
        super(question);
        this.aliment = aliment;
    }

    @Override
    public String getValue() {
        return aliment.getAlim_nom_fr();
    }
}
