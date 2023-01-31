package com.apisae.api.models.sondage;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name="sondage", nullable = false)
    private Sondage sondage;

}
