package com.apisae.api.services.sondage;


import com.apisae.api.models.sondage.SondageDTO;

import java.util.List;

public interface IServiceSondage{

    List<SondageDTO> findAllSondage();
}
