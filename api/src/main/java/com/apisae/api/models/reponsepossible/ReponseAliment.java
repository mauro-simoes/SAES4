package com.apisae.api.models.reponsepossible;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reponse_aliment")
public class ReponseAliment extends ReponsePossible{

    @ManyToOne
    @JoinColumn(name="aliment", nullable = false)
    private Aliment aliment;
}
