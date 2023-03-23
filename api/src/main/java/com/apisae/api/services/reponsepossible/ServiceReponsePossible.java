package com.apisae.api.services.reponsepossible;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.repositories.sondage.QuestionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceReponsePossible {

    private final QuestionRepository questionRepository;

    public List<ReponsePossible> getReponsePossibleByQuestionID(@NonNull Long questionID){
        Question question = questionRepository.findById(questionID)
                .orElse(null);
        if(question != null  || question.getReponses() != null)
            return question.getReponses();
        return new ArrayList<>();
    }

}
