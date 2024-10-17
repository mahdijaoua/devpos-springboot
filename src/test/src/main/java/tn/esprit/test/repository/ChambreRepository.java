package tn.esprit.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.test.entity.Chambre;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {

    @Query("SELECT c FROM Chambre c JOIN c.reservations r JOIN r.etudiants e WHERE e.cin = :cin")
    Chambre findChambreByEtudiantCIN(@Param("cin") long cin);
}
