package com.apisae.api.services.sondage;

import com.apisae.api.models.sondage.Question;
import com.apisae.api.repositories.sondage.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question findById(Long id){
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("La question avec l'id %d n'a pas été trouvé",id)));
    }


}
