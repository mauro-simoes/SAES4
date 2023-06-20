package com.apisae.api.repositories.sondage;

import com.apisae.api.models.reponsepossible.ReponsePossible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReponsePossibleRepository extends JpaRepository<ReponsePossible,Long> {

    Optional<ReponsePossible> findById(Long id);

}
