package com.apisae.api.services.aliment;

import com.apisae.api.models.reponsepossible.Aliment;
import com.apisae.api.models.reponsepossible.AlimentDTO;
import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AlimentDTOMapper implements Function<Aliment, AlimentDTO> {
    @Override
    public AlimentDTO apply(Aliment aliment) {
        return new AlimentDTO(
                aliment.getAlim_code(),
                aliment.getAlim_nom_fr()
        );
    }
}
