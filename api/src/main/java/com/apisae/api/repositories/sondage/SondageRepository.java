package com.apisae.api.repositories.sondage;

import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.projections.SondageProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SondageRepository extends JpaRepository<Sondage,Long> {

    @Query(value="SELECT s.id as id,s.nom as nom FROM Sondage s")
    List<SondageProjection> findAllSondage();

}

