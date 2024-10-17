package tn.esprit.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.test.entity.Foyer;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer,Long> {

}
