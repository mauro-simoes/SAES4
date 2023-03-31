package com.apisae.api.models.sondage;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @OneToMany(mappedBy = "sondage")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString(){
        return "{id = " + id + ", nom = " + nom +"}";
    }

}
