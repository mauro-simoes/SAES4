package com.apisae.api.models.authentification;

/**
 * @author Mauro Simoes
 */

public class RequeteAuth extends Requete{

    public RequeteAuth(String email, String password){
        super(email, password);
    }

    @Override
    public Boolean estValide() {
        return mailEstValide() && passwordEstValide();
    }
}
