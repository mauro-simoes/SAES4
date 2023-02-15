package com.apisae.api.services.sondage;

import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageQuestionDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SondageQuestionDTOMapper implements Function<Sondage, SondageQuestionDTO> {
    @Override
    public SondageQuestionDTO apply(Sondage sondage) {
        return new SondageQuestionDTO(
                sondage.getQuestions()
        );
    }
}



