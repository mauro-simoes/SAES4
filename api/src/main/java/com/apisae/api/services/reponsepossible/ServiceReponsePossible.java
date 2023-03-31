package com.apisae.api.services.reponsepossible;

import com.apisae.api.enums.TypeReponseQuestion;
import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.repositories.sondage.QuestionRepository;
import com.apisae.api.repositories.sondage.ReponsePossibleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceReponsePossible {

    private final QuestionRepository questionRepository;

    private final ReponsePossibleRepository reponsePossibleRepository;

    public List<ReponsePossible> getReponsePossibleByQuestionID(@NonNull Long questionID){
        Question question = questionRepository.findById(questionID)
                .orElse(null);
        if(question != null  && question.getReponses() != null && question.getTypeReponse() != TypeReponseQuestion.TEXTE)
            return question.getReponses();
        return new ArrayList<>();
    }

    public ReponsePossible findByID(@NonNull Long reponsePossibleID){
        return reponsePossibleRepository.findById(reponsePossibleID)
                .orElseThrow(() -> new RuntimeException(String.format("La reponse possible avec l'id %d n'a pas été trouvé",reponsePossibleID)));
    }

    public ReponsePossible save(@NonNull ReponsePossible reponsePossible){
        return reponsePossibleRepository.save(reponsePossible);
    }

}
