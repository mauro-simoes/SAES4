package com.apisae.api.models.reponsepossible;


import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reponse_texte")
public class ReponseTexte extends ReponsePossible {

    private String texte;
    @Override
    public String getValue() {
        return texte;
    }
}
