package com.apisae.api.services.sondage;

import com.apisae.api.projections.SondageProjection;
import com.apisae.api.repositories.sondage.SondageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceSondage implements IServiceSondage{

    private final SondageRepository sondageRepository;

    @Override
    public List<SondageProjection> findAllSondage() throws RuntimeException{
        return sondageRepository.findAllSondage();
    }
}
