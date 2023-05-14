package com.apisae.api.services.reponse;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.repositories.reponse.ReponseRepository;
import com.apisae.api.repositories.sondage.ReponsePossibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceReponse implements IServiceReponse{

    private final ReponseRepository reponseRepository;

    private final ReponsePossibleRepository reponsePossibleRepository;

    public Map<String, Long> top10Aliment() {
        Map<String, Long> result = new LinkedHashMap<>();
        List<Object[]> rows = reponseRepository.findTop10Aliment();

        for (Object[] row : rows) {
            String name = (String) row[0];
            long counter = (long) row[1];
            result.put(name, counter);
        }
        return result;

    }

    public Map<String, Long> top10grpAliment() {
        Map<String, Long> result = new LinkedHashMap<>();
        List<Object[]> rows = reponseRepository.findTop10GrpAliment();

        for (Object[] row : rows) {
            String name = (String) row[0];
            long counter = (long) row[1];
            result.put(name, counter);
        }
        return result;
    }

    public Map<String, Long> getAllReponses(Long questionID) {
        Map<String, Long> result = new LinkedHashMap<>();
        List<Object[]> rows = reponseRepository.findAllReponsesWithCount(questionID);

        for (Object[] row : rows) {
            Long idReponse = (Long) row[0];
            ReponsePossible reponsePossible = reponsePossibleRepository.findById(idReponse)
                    .orElseThrow(() -> new RuntimeException("La réponse " + idReponse + " n'a pas été trouvé."));
            String name = reponsePossible.getValue();
            long counter = (long) row[1];
            result.put(name, counter);
        }
        return result;
    }

    public Map<String, Long> getTop10Reponses(Long questionID) {
        Map<String, Long> result = new LinkedHashMap<>();
        List<Object[]> rows = reponseRepository.findTop10ReponsesWithCount(questionID);

        for (Object[] row : rows) {
            Long idReponse = (Long) row[0];
            ReponsePossible reponsePossible = reponsePossibleRepository.findById(idReponse)
                    .orElseThrow(() -> new RuntimeException("La réponse " + idReponse + " n'a pas été trouvé."));
            String name = reponsePossible.getValue();
            long counter = (long) row[1];
            result.put(name, counter);
        }
        return result;
    }

}
