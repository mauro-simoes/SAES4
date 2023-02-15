package com.apisae.api.models.authentification;

import lombok.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Mauro Simoes
 */

@Getter
public class RequeteCreationCompte extends Requete{
    private final static int AGE_MIN = 13;
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
    public Boolean estValide() {
        return mailEstValide() &&
                passwordEstValide() &&
                champEstValide(nom) &&
                champEstValide(prenom) &&
                champEstValide(ville) &&
                dateNaissanceEstValide();
    }

    /**
     * Verifie si l'utilisateur a plus de 13 ans
     *
     * @return vrai si l'utilisateur a plus de 13 ans, faux sinon
     */
    private Boolean dateNaissanceEstValide(){
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        c.add(Calendar.YEAR, -AGE_MIN);
        long result = c.getTimeInMillis();
        return dateNaissance != null && dateNaissance.before(new java.util.Date(result));
    }
}