package com.apisae.api.repositories.sondage;

import com.apisae.api.models.sondage.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SondageRepository extends JpaRepository<Sondage,Long> {

}

