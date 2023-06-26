package com.apisae.api.services.reponsepossible;

import com.apisae.api.enums.TypeReponseQuestion;
import com.apisae.api.models.reponsepossible.Aliment;
import com.apisae.api.models.reponsepossible.ReponseAliment;
import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.reponsepossible.ReponseTexte;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.repositories.sondage.QuestionRepository;
import com.apisae.api.repositories.sondage.ReponsePossibleRepository;
import com.apisae.api.services.aliment.ServiceAliment;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceReponsePossible {

    private final QuestionRepository questionRepository;

    private final ReponsePossibleRepository reponsePossibleRepository;

    private final ServiceAliment serviceAliment;

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

    public void addAllAlimentsToQuestion(@NonNull Long questionID){
        List<Aliment> aliments = serviceAliment.getAllAliments();

        Question question = questionRepository.findById(questionID)
                .orElseThrow(() -> new RuntimeException(String.format("La question %o n'a pas été trouvé.",questionID)));

        for (Aliment aliment : aliments){
            ReponsePossible nouvelleReponseAliment = new ReponseAliment(question,aliment);
            reponsePossibleRepository.save(nouvelleReponseAliment);
        }

    }

    public void addReponseTexte(@NonNull Long questionID,@NonNull String texte){

        Question question = questionRepository.findById(questionID)
                .orElseThrow(() -> new RuntimeException(String.format("La question %o n'a pas été trouvé.",questionID)));

        ReponsePossible nouvelleReponseTexte = new ReponseTexte(question,texte);
        reponsePossibleRepository.save(nouvelleReponseTexte);

    }

}
