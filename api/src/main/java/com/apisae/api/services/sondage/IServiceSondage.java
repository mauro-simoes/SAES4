package com.apisae.api.services.sondage;


import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.models.sondage.SondageQuestionDTO;

import java.util.List;
import java.util.Optional;

public interface IServiceSondage{

    List<SondageDTO> findAllSondage();

    SondageQuestionDTO getQuestion(Long id);
}
