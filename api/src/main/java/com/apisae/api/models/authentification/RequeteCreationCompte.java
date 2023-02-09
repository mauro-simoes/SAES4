package com.apisae.api.models.authentification;

import lombok.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

@Getter
public class RequeteCreationCompte extends Requete{

    private final String nom;
    private final String prenom;
    private final String ville;
    private final Date dateNaissance;

    public RequeteCreationCompte(String email, String password,String nom, String prenom, String ville, Date dateNaissance){
        super(email, password);
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.dateNaissance = dateNaissance;
    }

    @Override
    protected Boolean contenuEstValide() {
        return mailEstValide() &&
                passwordEstValide() &&
                champEstValide(nom) &&
                champEstValide(prenom)  &&
                champEstValide(ville) &&
                dateNaissanceEstValide();
    }

    private Boolean dateNaissanceEstValide(){
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        c.add(Calendar.YEAR, -13);
        long result = c.getTimeInMillis();
        return dateNaissance != null && dateNaissance.before(new java.util.Date(result));
    }
}