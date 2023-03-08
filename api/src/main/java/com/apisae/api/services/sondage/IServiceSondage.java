package com.apisae.api.services.sondage;


import com.apisae.api.models.sondage.SondageDTO;

import java.util.List;
import java.util.Map;

public interface IServiceSondage{

    List<Map<String,Object>> findAllSondage();

    Map<Long,String> getQuestion(Long id);

    Map<String,List<String>>  getReponsesUtilisateurSondage(Long idSondage);

    boolean utilisateurARepondu(Long idSondage);

    Integer nbQuestionSondage(Long idSondage);
}
