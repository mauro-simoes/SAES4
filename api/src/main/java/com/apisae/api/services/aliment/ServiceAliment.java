package com.apisae.api.services.aliment;

import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.reponsepossible.AlimentDTO;
import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.models.sondage.Question;
import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageDTO;
import com.apisae.api.repositories.aliment.AlimentRepository;
import com.apisae.api.repositories.reponse.ReponseRepository;
import com.apisae.api.repositories.sondage.SondageRepository;
import com.apisae.api.services.sondage.SondageDTOMapper;
import com.apisae.api.services.user.UserOutils;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.AliasedExpression;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ServiceAliment implements IServiceAliment {

    private final AlimentRepository alimentRepository;
    private final AlimentDTOMapper alimentDTOMapper;

    public List<Map<String,Object>> findAllSondage() {
        List<AlimentDTO> aliment = alimentRepository.findAll()
                .stream()
                .map(alimentDTOMapper)
                .toList();

        Map<String,Object> aliment_map = new LinkedHashMap<>();
        List<Map<String,Object>> all_aliment = new ArrayList<>();

        for (AlimentDTO s : aliment){

            aliment_map.put("id",s.id());
            aliment_map.put("nom",s.alim_nom_fr());

            all_aliment.add(aliment_map);
            aliment_map = new LinkedHashMap<>();
        }
        return all_aliment;
    }

}
