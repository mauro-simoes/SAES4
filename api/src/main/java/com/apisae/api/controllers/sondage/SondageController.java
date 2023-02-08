package com.apisae.api.controllers.sondage;

import com.apisae.api.projections.SondageProjection;
import com.apisae.api.services.sondage.IServiceSondage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(path = "/api/sondage")
@RestController
@ResponseBody
@RequiredArgsConstructor
public class SondageController {

    private final IServiceSondage serviceSondage;

    @GetMapping(path ="/get-all")
    public List<SondageProjection> getAllSondage(){
        return serviceSondage.findAllSondage();
    }
}
