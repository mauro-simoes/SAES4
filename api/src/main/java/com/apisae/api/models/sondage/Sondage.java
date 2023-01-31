package com.apisae.api.models.sondage;

import com.apisae.api.models.choix.Choix;
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

    @Column(name = "nombre_reponse", nullable = false)
    private int nbResponseAttendues;

    @OneToMany(mappedBy = "sondage")
    private List<Question> questions;

    @OneToMany(mappedBy = "sondage")
    private List<Choix> choix;
}
