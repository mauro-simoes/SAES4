package com.apisae.api.models.authentification;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public abstract class Requete {

    private final String email;
    private final String password;

    public Requete(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Boolean estValide(){
        return contenuEstValide();
    }

    protected abstract Boolean contenuEstValide();

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
        return champEstValide(password) && password.length() >= 8;
    }

    protected static boolean champEstValide(String champ){
        return champ != null && !champ.isBlank() && !champ.isEmpty();
    }


}
