package com.apisae.api.controllers.sondage;

import com.apisae.api.models.error.ErrorBody;
import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.models.sondage.SondageQuestionDTO;
import com.apisae.api.services.sondage.IServiceSondage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/sondage")
@RestController
@RequiredArgsConstructor
public class SondageController {

    private final IServiceSondage serviceSondage;

    @GetMapping(path ="/get-all")
    public List<SondageDTO> getAllSondage(){
        return serviceSondage.findAllSondage();
    }

    @GetMapping(path ="/get/{id}")
    public SondageQuestionDTO getQuestions(@PathVariable(name = "id") Long id){
        return serviceSondage.getQuestion(id);
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

}
