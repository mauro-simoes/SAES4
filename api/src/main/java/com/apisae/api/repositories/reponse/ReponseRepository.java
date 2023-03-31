package com.apisae.api.repositories.reponse;

import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.reponse.ReponseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, ReponseId> {

    Optional<List<Reponse>> findAllByUtilisateur_EmailAndReponse_Question_Id(String email, Long idQuestion);

}
