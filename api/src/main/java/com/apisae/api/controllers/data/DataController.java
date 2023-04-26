package com.apisae.api.controllers.data;

import com.apisae.api.models.error.ErrorBody;
import com.apisae.api.services.reponse.IServiceReponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/data")
@RestController
@RequiredArgsConstructor
public class DataController {

    private final IServiceReponse serviceReponse;

    @GetMapping(path ="/top-Aliment")
    public ResponseEntity<Object> getTopAliment(){
        Map<String,Long>  top;
        try{
            top = serviceReponse.top10Aliment();
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(new ErrorBody(e.getMessage()));
        }
        return ResponseEntity.ok(top);
    }

    @GetMapping(path ="/top-grp-Aliment")
    public ResponseEntity<Object> getTopGrpAliment() {
        Map<String, Long> top;
        try {
            top = serviceReponse.top10grpAliment();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorBody(e.getMessage()));
        }
        return ResponseEntity.ok(top);
    }


}
