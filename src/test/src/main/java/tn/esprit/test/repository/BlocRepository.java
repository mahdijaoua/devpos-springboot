package tn.esprit.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.test.entity.Bloc;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {

    @Query("SELECT b FROM Bloc b WHERE b.foyer IS NULL")
    List<Bloc> getBlocsNonAffectes();

    List<Bloc> findAllByFoyerIsNull();

}
