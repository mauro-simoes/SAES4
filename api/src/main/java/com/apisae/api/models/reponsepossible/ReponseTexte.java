package com.apisae.api.models.reponsepossible;


import com.apisae.api.models.sondage.Question;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reponse_texte")
public class ReponseTexte extends ReponsePossible {

    private String texte;

    public ReponseTexte(Question question, String texte){
        super(question);
        this.texte = texte;
    }

    @Override
    public String getValue() {
        return texte;
    }
}
