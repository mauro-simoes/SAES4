package com.apisae.api.services.sondage;


import com.apisae.api.projections.SondageProjection;

import java.util.List;

public interface IServiceSondage{

    List<SondageProjection> findAllSondage() throws RuntimeException;
}
