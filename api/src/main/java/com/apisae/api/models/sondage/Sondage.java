package com.apisae.api.models.sondage;

import com.apisae.api.models.reponse.Reponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sondage")
public class Sondage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(mappedBy = "sondage")
    private List<Question> questions;

}
