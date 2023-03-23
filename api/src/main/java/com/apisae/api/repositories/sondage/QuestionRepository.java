package com.apisae.api.repositories.sondage;

import com.apisae.api.models.sondage.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
