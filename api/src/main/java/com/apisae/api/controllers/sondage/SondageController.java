package com.apisae.api.controllers.sondage;

import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.models.sondage.SondageQuestionDTO;
import com.apisae.api.services.sondage.IServiceSondage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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


}
