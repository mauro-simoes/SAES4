package com.apisae.api.services.sondage;

import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class SondageDTOMapper implements Function<Sondage, SondageDTO> {
    @Override
    public SondageDTO apply(Sondage sondage) {
        return new SondageDTO(
                sondage.getId(),
                sondage.getNom()
        );
    }
}
