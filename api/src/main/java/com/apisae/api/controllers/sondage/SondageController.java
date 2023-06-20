package com.apisae.api.controllers.sondage;

import com.apisae.api.enums.TypeReponseQuestion;
import com.apisae.api.models.authentification.RequeteCreationCompte;
import com.apisae.api.models.error.ErrorBody;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.models.sondage.ReponseBody;
import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.repositories.sondage.QuestionRepository;
import com.apisae.api.services.reponse.IServiceReponse;
import com.apisae.api.services.sondage.IServiceSondage;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:19000"})
@RequestMapping(path = "/api/sondage")
@RestController
@RequiredArgsConstructor
public class SondageController {

    private final IServiceSondage serviceSondage;

    private final QuestionRepository questionRepository;


    @GetMapping(path ="/get-all")
    public List<Map<String,Object>> getAllSondage(){
        return serviceSondage.findAllSondage();
    }

    @GetMapping(path ="/get-question-of-sondage/{id}")
    public ResponseEntity<Object> getQuestions(@PathVariable(name = "id") Long id){

        List<Map<String,Object>>  questions ;

        try{
            questions = serviceSondage.getQuestion(id);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorBody(e.getMessage()));
        }
        return ResponseEntity.ok(questions);
    }

    @GetMapping(path ="/get-reponses-utilisateur/{id}")
    public ResponseEntity<Object> getReponsesUtilisateurSondage(@PathVariable(name = "id") Long id){
        Map<String,List<String>>  questionsReponses;
        try{
            questionsReponses = serviceSondage.getReponsesUtilisateurSondage(id);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorBody(e.getMessage()));
        }
        return ResponseEntity.ok(questionsReponses);
    }

    @PostMapping(path = "/addQuestion")
    public ResponseEntity<Object> addQuestion(@NonNull @RequestBody Map<String,String> questionBody){
        Question question = new Question();
        question.setTexte(questionBody.get("texte"));
        question.setNbResponseMax(Integer.valueOf(questionBody.get("nbReponseMax")));
        question.setNbResponseMin(Integer.valueOf(questionBody.get("nbReponseMin")));
        question.setTypeReponse(Enum.valueOf(TypeReponseQuestion.class,questionBody.get("typeReponse")));

        Sondage sondage = serviceSondage.findByID(Long.valueOf(questionBody.get("idSondage")));

        question.setSondage(sondage);

        return ResponseEntity.ok().body(question);
    }

    @PostMapping(path = "/repondre")
    public ResponseEntity<Object> repondre(@NonNull @RequestBody ReponseBody reponseBody){
        Sondage sondage = serviceSondage.findByID(reponseBody.getIdSondage());

        if (reponseBody.getReponses().size() != sondage.getQuestions().size()){
            return ResponseEntity.badRequest().body(new ErrorBody("Le sondage n'a pas été complété"));
        }

        for (String idQuestion : reponseBody.getReponses().keySet()){
            Question question = questionRepository.findById(Long.valueOf(idQuestion)).orElse(null);
            if (question == null)
                return ResponseEntity.badRequest().body(new ErrorBody("Invalid question " +  idQuestion));
            for (String reponse : reponseBody.getReponses().get(idQuestion))
                serviceSondage.repondre(question,reponse);
        }

        return ResponseEntity.ok().build();
    }

}
