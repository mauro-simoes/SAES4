package com.apisae.api.repositories.reponsepossible;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReponsePossibleRepository extends JpaRepository<ReponsePossible,Long> {

    Optional<List<ReponsePossible>> findAllByQuestionId(@NonNull Long questionID);

}
