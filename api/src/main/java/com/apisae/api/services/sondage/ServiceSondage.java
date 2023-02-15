package com.apisae.api.services.sondage;

import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.models.sondage.SondageQuestionDTO;
import com.apisae.api.repositories.sondage.SondageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ServiceSondage implements IServiceSondage {

    private final SondageRepository sondageRepository;
    private final SondageDTOMapper sondageDTOMapper;
    private final SondageQuestionDTOMapper sondageQuestionDTOMapper;

    public List<SondageDTO> findAllSondage() {
        return sondageRepository.findAll()
                .stream()
                .map(sondageDTOMapper)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public SondageQuestionDTO getQuestion(Long id) {
        Optional<SondageQuestionDTO> result = sondageRepository.findById(id)
                .map(sondageQuestionDTOMapper);

        if(result.isPresent()) {
            return result.get();
        }else {
            throw new RuntimeException();
        }
    }
}
