package com.apisae.api.services.sondage;

import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.models.sondage.SondageQuestionDTO;
import com.apisae.api.repositories.reponse.ReponseRepository;
import com.apisae.api.repositories.sondage.SondageRepository;
import com.apisae.api.services.user.UserOutils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceSondage implements IServiceSondage {

    private final SondageRepository sondageRepository;
    private final SondageDTOMapper sondageDTOMapper;
    private final SondageQuestionDTOMapper sondageQuestionDTOMapper;

    private final ReponseRepository reponseRepository;

    public List<SondageDTO> findAllSondage() {
        return sondageRepository.findAll()
                .stream()
                .map(sondageDTOMapper)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public SondageQuestionDTO getQuestion(Long id) {
        Optional<SondageQuestionDTO> result = sondageRepository.findById(id)
                .map(sondageQuestionDTOMapper);

        if(result.isPresent()) {
            return result.get();
        }else {
            throw new RuntimeException();
        }
    }
    public Map<String,List<String>>  getReponsesUtilisateurSondage(Long idSondage){

        String email = UserOutils.getCurrentUserEmail();
        Sondage sondage = sondageRepository.findById(idSondage)
                .orElseThrow(() -> new RuntimeException(String.format("Le sondage avec l'id %d n'a pas été trouvé",idSondage)));
        Map<String,List<String>> questionReponse = new LinkedHashMap<>();

        if (!utilisateurARepondu(idSondage)) return questionReponse;

        for (Question question : sondage.getQuestions()){
            List<Reponse> reponseList = reponseRepository.findAllByMailUtilisateurAndQuestionId(email, question.getId())
                    .orElse(new ArrayList<>());
            List<String> valeurReponseList = new ArrayList<>();
            for (Reponse reponse : reponseList)
                valeurReponseList.add(reponse.getReponse().getValue());
            questionReponse.put(question.getTexte(),valeurReponseList);
        }

        return questionReponse;
    }

    public boolean utilisateurARepondu(Long idSondage){
        boolean aRepondu = true;

        Sondage sondage = sondageRepository.findById(idSondage)
                .orElseThrow(() -> new RuntimeException(String.format("Le sondage avec l'id %d n'a pas été trouvé",idSondage)));
        String email = UserOutils.getCurrentUserEmail();

        for (Question question : sondage.getQuestions()){
            List<Reponse> reponseList = reponseRepository.findAllByMailUtilisateurAndQuestionId(email, question.getId())
                    .orElse(new ArrayList<>());
            if (reponseList.size() < question.getNbResponseMin() || reponseList.size() > question.getNbResponseMax()) aRepondu = false;
        }

        return aRepondu;
    }


}
