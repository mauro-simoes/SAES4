package com.apisae.api.services.sondage;


import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

public interface IServiceSondage{

    List<Map<String,Object>> findAllSondage();

    List<Map<String,Object>> getQuestion(Long id);

    Map<String,List<String>>  getReponsesUtilisateurSondage(Long idSondage);

    boolean utilisateurARepondu(Long idSondage);

    Integer nbQuestionSondage(Long idSondage);

    public Sondage findByID(@NonNull Long idSondage);

    public Reponse repondre(@NonNull Question question, @NonNull String reponse);
}
