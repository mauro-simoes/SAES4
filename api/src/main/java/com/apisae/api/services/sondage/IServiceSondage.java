package com.apisae.api.services.sondage;


import com.apisae.api.models.sondage.Sondage;

import java.util.List;

public interface IServiceSondage{

    List<Sondage> findAll() throws RuntimeException;
}
