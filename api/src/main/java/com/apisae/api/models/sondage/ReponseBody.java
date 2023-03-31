package com.apisae.api.models.sondage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReponseBody {

    private Long idSondage;

    private Map<String, List<String>> reponses;

}
