package com.apisae.api.services.sondage;


import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.models.sondage.SondageQuestionDTO;

import java.util.List;
import java.util.Map;

public interface IServiceSondage{

    List<SondageDTO> findAllSondage();

    SondageQuestionDTO getQuestion(Long id);

    Map<String,List<String>>  getReponsesUtilisateurSondage(Long idSondage);

    boolean utilisateurARepondu(Long idSondage);
}
