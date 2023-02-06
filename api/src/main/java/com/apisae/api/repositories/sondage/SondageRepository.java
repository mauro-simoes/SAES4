package com.apisae.api.repositories.sondage;

import com.apisae.api.models.sondage.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SondageRepository extends JpaRepository<Sondage,Long> {
    List<Sondage> findAll();
}
