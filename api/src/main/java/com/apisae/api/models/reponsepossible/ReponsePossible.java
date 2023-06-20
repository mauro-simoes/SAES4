package com.apisae.api.models.reponsepossible;


import com.apisae.api.models.sondage.Question;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reponse_possible")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ReponsePossible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GenericGenerator(
//            name = "sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @Parameter(name = "sequence_name", value = "reponse_possible_seq"),
//                    @Parameter(name = "initial_value", value = "1"),
//                    @Parameter(name = "increment_size", value = "1")
//            }
//    )
    private Long id;

    @ManyToOne
    @JoinColumn(name="question", nullable = false)
    private Question question;

    public ReponsePossible(Question question) {
        this.question = question;
    }

    public abstract String getValue();

}
