package com.apisae.api.services.sondage;

import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.repositories.sondage.SondageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceSondage implements IServiceSondage{

    private final SondageRepository sondageRepository;
    private final SondageDTOMapper sondageDTOMapper;

    public List<SondageDTO> findAllSondage(){
        return sondageRepository.findAll()
                .stream()
                .map(sondageDTOMapper)
                .collect(Collectors.toList())
                ;
    }
}
