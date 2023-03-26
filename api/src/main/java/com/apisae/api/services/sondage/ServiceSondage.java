package com.apisae.api.services.sondage;

import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.repositories.reponse.ReponseRepository;
import com.apisae.api.repositories.sondage.SondageRepository;
import com.apisae.api.services.user.UserOutils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ServiceSondage implements IServiceSondage {

    private final SondageRepository sondageRepository;
    private final SondageDTOMapper sondageDTOMapper;

    private final ReponseRepository reponseRepository;

    public List<Map<String,Object>> findAllSondage() {
        List<SondageDTO> sondage = sondageRepository.findAll()
                .stream()
                .map(sondageDTOMapper)
                .toList();

        Map<String,Object> sondage_map = new LinkedHashMap<>();
        List<Map<String,Object>> allSondage = new ArrayList<>();

        for (SondageDTO s : sondage){

            sondage_map.put("id",s.id());
            sondage_map.put("nom",s.nom());
            sondage_map.put("aRepondu",utilisateurARepondu(s.id()));
            sondage_map.put("nbQuestion",nbQuestionSondage(s.id()));

            allSondage.add(sondage_map);
            sondage_map = new LinkedHashMap<>();
        }
        return allSondage;
    }

    public List<Map<String,Object>> getQuestion(Long id) {
        Sondage sondage = sondageRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Le sondage avec l'id %d n'a pas été trouvé",id)));
        List<Map<String,Object>> allquestion = new ArrayList<>();
        Map<String,Object> question_map = new LinkedHashMap<>();

        for (Question question : sondage.getQuestions()) {
            question_map.put("id",question.getId());
            question_map.put("contenu", question.getTexte());
            question_map.put("type",question.getTypeReponse().toString());
            question_map.put("nbReponseMin",question.getNbResponseMin());
            question_map.put("nbReponseMax",question.getNbResponseMax());

            allquestion.add(question_map);
            question_map = new LinkedHashMap<>();

            //question_map.put(question.getId().toString(),new ArrayList<>(List.of(question.getTexte(),question.getTypeReponse().toString())));
        }

        return allquestion;
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


    public Integer nbQuestionSondage(Long idSondage) {
        Sondage sondage = sondageRepository.findById(idSondage).orElseThrow(() -> new RuntimeException(String.format("Le sondage avec l'id %d n'a pas été trouvé",idSondage)));
        return sondage.getQuestions().size();
    }


}
