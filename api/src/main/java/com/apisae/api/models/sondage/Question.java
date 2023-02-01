package com.apisae.api.models.sondage;

import com.apisae.api.enums.TypeReponseQuestion;
import com.apisae.api.models.reponsepossible.ReponsePossible;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "texte", nullable = false, unique = true)
    private String texte;

    @Column(name = "nombre_reponse_min", nullable = false)
    private int nbResponseMin;

    @Column(name = "nombre_reponse_max", nullable = false)
    private int nbResponseMax;

    @ManyToOne
    @JoinColumn(name="sondage", nullable = false)
    private Sondage sondage;

    @Enumerated(EnumType.STRING)
    @NonNull
    private TypeReponseQuestion typeReponse;

    @OneToMany(mappedBy = "question")
    private List<ReponsePossible> reponses;

}
