package com.apisae.api.models.reponsepossible;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aliment")
public class Aliment extends ReponsePossible {

    @Id
    private Long id;

}
