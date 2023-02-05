package com.apisae.api.services.authentification;

import com.apisae.api.models.authentification.ReponseAuth;
import com.apisae.api.models.authentification.RequeteAuth;
import com.apisae.api.models.authentification.RequeteCreationCompte;
import org.springframework.lang.NonNull;

public interface IServiceAuthentifiaction {

    ReponseAuth creerCompte(@NonNull RequeteCreationCompte request);

    ReponseAuth authentifier(@NonNull RequeteAuth request);

}
