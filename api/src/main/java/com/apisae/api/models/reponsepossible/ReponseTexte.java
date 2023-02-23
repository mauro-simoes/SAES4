package com.apisae.api.models.reponsepossible;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reponse_texte")
public class ReponseTexte extends ReponsePossible {

    @Column(unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String texte;
    @Override
    public String getValue() {
        return texte;
    }
}
