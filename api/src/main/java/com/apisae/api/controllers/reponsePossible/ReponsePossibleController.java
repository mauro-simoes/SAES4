package com.apisae.api.controllers.reponsePossible;

import com.apisae.api.models.error.ErrorBody;
import com.apisae.api.models.reponsepossible.ReponsePossibleDTO;
import com.apisae.api.services.reponsepossible.ReponsePossibleDTOMapper;
import com.apisae.api.services.reponsepossible.ServiceReponsePossible;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/reponse-question")
@RestController
@RequiredArgsConstructor
public class ReponsePossibleController {

    private final ServiceReponsePossible serviceReponsePossible;

    private final ReponsePossibleDTOMapper reponsePossibleDTOMapper;

    @GetMapping(path ="/{id}")
    public List<ReponsePossibleDTO> getAllReponse(@PathVariable(name = "id") Long questionID){
        return serviceReponsePossible.getReponsePossibleByQuestionID(questionID)
                .stream()
                .map(reponsePossibleDTOMapper)
                .toList();
    }

    @GetMapping(path ="/addAllAliments/{questionID}")
    public ResponseEntity<Object> addAllAlimentsToQuestion(@PathVariable(name = "questionID") Long questionID){
        try{
            serviceReponsePossible.addAllAlimentsToQuestion(questionID);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorBody(e.getMessage()));
        }
        return ResponseEntity.ok().build();
    }


}


