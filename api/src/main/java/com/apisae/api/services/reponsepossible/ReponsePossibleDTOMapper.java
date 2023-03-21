package com.apisae.api.services.reponsepossible;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.reponsepossible.ReponsePossibleDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ReponsePossibleDTOMapper implements Function<ReponsePossible, ReponsePossibleDTO> {

    @Override
    public ReponsePossibleDTO apply(ReponsePossible reponsePossible) {
        return new ReponsePossibleDTO(
                reponsePossible.getId(),
                reponsePossible.getValue());
    }
}
