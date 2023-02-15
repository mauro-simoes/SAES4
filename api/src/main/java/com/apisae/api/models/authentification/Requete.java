package com.apisae.api.models.authentification;

import lombok.Getter;

import java.util.regex.Pattern;

/**
 * @author Mauro Simoes
 */

@Getter
public abstract class Requete {
    private final static int LONGUEUR_MIN_PASSWORD = 8;
    private final String email;
    private final String password;

    public Requete(String email, String password){
        this.email = email;
        this.password = password;
    }

    /**
     * Verifie si une requete est valide
     *
     * @return vrai si la requete est valide, faux sinon
     */
    public abstract Boolean estValide();

    /**
     * Verifie si un mail est valide
     *
     * @return vrai si le mail est valide, faux sinon
     */
    protected Boolean mailEstValide(){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return champEstValide(email) &&
                Pattern.compile(regex).matcher(email).matches();
    }

    /**
     * Verifie si le mot de passe est valide (plus de 8 caracteres)
     *
     * @return vrai si le mot de passe est valide, faux sinon
     */
    protected Boolean passwordEstValide(){
        return champEstValide(password) && password.length() >= LONGUEUR_MIN_PASSWORD;
    }

    /**
     * Verifie si un champ est valide (pas vide ou null)
     *
     * @return vrai si le champ est valide, faux sinon
     */
    protected static boolean champEstValide(String champ){
        return champ != null && !champ.isBlank() && !champ.isEmpty();
    }


}
