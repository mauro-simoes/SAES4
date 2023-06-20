package com.apisae.api.services.reponse;

import java.util.Map;

public interface IServiceReponse {

    //2 function named

    Map<String,Long> top10Aliment();

    Map<String,Long> top10grpAliment();

    Map<String, Long> getAllReponses(Long questionID);

    Map<String, Long> getTop10Reponses(Long questionID);

}
