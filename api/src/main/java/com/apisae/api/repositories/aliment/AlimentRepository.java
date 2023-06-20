package com.apisae.api.repositories.aliment;

import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.reponse.ReponseId;
import com.apisae.api.models.reponsepossible.Aliment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlimentRepository extends JpaRepository<Aliment, Long> {
}

