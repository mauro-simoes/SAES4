package com.apisae.api.models.authentification;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public abstract class Requete {
    private final static int LONGUEUR_MIN_PASSWORD = 8;
    private final String email;
    private final String password;

    public Requete(String email, String password){
        this.email = email;
        this.password = password;
    }

    public abstract Boolean estValide();

    protected Boolean mailEstValide(){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email != null &&
                !email.isEmpty() &&
                !email.isBlank() &&
                Pattern.compile(regex)
                        .matcher(email)
                        .matches();
    }

    protected Boolean passwordEstValide(){
        return champEstValide(password) && password.length() >= LONGUEUR_MIN_PASSWORD;
    }

    protected static boolean champEstValide(String champ){
        return champ != null && !champ.isBlank() && !champ.isEmpty();
    }


}
