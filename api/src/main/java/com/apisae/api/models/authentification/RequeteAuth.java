package com.apisae.api.models.authentification;


public class RequeteAuth extends Requete{

    public RequeteAuth(String email, String password){
        super(email, password);
    }

    @Override
    protected Boolean contenuEstValide() {
        return mailEstValide() && passwordEstValide();
    }
}
