package com.apisae.api.repositories.reponse;

import com.apisae.api.models.reponse.Reponse;
import com.apisae.api.models.reponse.ReponseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, ReponseId> {

    Optional<List<Reponse>> findAllByUtilisateur_EmailAndReponse_Question_Id(String email, Long idQuestion);

    @Query(nativeQuery = true,value="SELECT a.alim_nom_fr AS name, COUNT(r.reponse) AS counter\n" +
            "FROM reponse r\n" +
            "INNER JOIN reponse_aliment ra ON r.reponse = ra.id\n" +
            "INNER JOIN aliment a ON ra.aliment = a.alim_code\n" +
            "GROUP BY a.alim_nom_fr\n" +
            "ORDER BY counter DESC\n" +
            "LIMIT 10;")
    List<Object[]> findTop10Aliment();

    @Query(nativeQuery = true,value="SELECT a.alim_grp_nom_fr AS name, COUNT(r.reponse) AS counter\n" +
            "FROM reponse r\n" +
            "INNER JOIN reponse_aliment ra ON r.reponse = ra.id\n" +
            "INNER JOIN aliment a ON ra.aliment = a.alim_code\n" +
            "GROUP BY a.alim_grp_nom_fr\n" +
            "ORDER BY counter DESC\n" +
            "LIMIT 10;")
    List<Object[]> findTop10GrpAliment();

    @Query(nativeQuery = true,value="SELECT r.reponse AS id, COUNT(r.reponse) AS counter FROM reponse r INNER JOIN reponse_possible rp ON r.reponse = rp.id WHERE rp.question = :questionID GROUP BY r.reponse ORDER BY counter DESC;")
    List<Object[]> findAllReponsesWithCount(@Param("questionID") Long questionID);

    @Query(nativeQuery = true,value="SELECT r.reponse AS id, COUNT(r.reponse) AS counter FROM reponse r INNER JOIN reponse_possible rp ON r.reponse = rp.id WHERE rp.question = :questionID GROUP BY r.reponse ORDER BY counter DESC LIMIT 10;")
    List<Object[]> findTop10ReponsesWithCount(@Param("questionID") Long questionID);

}
