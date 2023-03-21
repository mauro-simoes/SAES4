package com.apisae.api.controllers.reponsePossible;

import com.apisae.api.models.error.ErrorBody;
import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.services.aliment.IServiceAliment;
import com.apisae.api.services.sondage.IServiceSondage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/reponse")
@RestController
@RequiredArgsConstructor
public class reponsePossibleController {

    private final IServiceAliment serviceAliment;

    @GetMapping(path ="/get-all-aliment")
    public List<Map<String,Object>> getAllReponse(){
        return serviceAliment.findAllSondage();
    }


}


