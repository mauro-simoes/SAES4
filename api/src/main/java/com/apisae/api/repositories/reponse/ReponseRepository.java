package com.apisae.api.repositories.reponse;

import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.reponse.ReponseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReponseRepository extends JpaRepository<Reponse, ReponseId> {

    @Query(value = "SELECT * FROM reponse r WHERE r.utilisateur IN (SELECT u.id FROM utilisateur u WHERE u.email = :email) " +
            "AND :idQuestion IN (SELECT rp.question FROM reponse_possible rp WHERE rp.id = r.reponse)", nativeQuery = true)
    Optional<List<Reponse>> findAllByMailUtilisateurAndQuestionId(@Param("email") String email, @Param("idQuestion") Long idQuestion);

}
