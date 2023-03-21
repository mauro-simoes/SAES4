package com.apisae.api.services.reponsepossible;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import com.apisae.api.repositories.reponsepossible.ReponsePossibleRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceReponsePossible {

    private final ReponsePossibleRepository reponsePossibleRepository;

    public List<ReponsePossible> getReponsePossibleByQuestionID(@NonNull Long questionID){
        return reponsePossibleRepository.findAllByQuestionId(questionID)
                .orElse(new ArrayList<>());
    }

}
