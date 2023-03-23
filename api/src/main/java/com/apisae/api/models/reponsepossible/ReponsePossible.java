package com.apisae.api.models.reponsepossible;


import com.apisae.api.models.sondage.Question;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reponse_possible")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ReponsePossible {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="question", nullable = false)
    private Question question;

    public abstract String getValue();

}
