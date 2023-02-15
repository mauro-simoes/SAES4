package com.apisae.api.models.sondage;

import java.util.List;

public record SondageQuestionDTO(
        List<Question> questions
){
}
