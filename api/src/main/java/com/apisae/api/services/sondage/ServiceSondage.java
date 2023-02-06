package com.apisae.api.services.sondage;

import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.repositories.sondage.SondageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceSondage implements IServiceSondage{

    private final SondageRepository sondageRepository;

    @Override
    public List<Sondage> findAll() throws RuntimeException{
        return sondageRepository.findAll();
    }
}
