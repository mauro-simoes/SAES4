package com.apisae.api.repositories.sondage;

import com.apisae.api.models.sondage.Sondage;
import com.apisae.api.models.sondage.SondageQuestionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface SondageRepository extends JpaRepository<Sondage,Long> {

}

