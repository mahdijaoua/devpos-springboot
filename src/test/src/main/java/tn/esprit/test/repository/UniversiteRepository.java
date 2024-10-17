package tn.esprit.test.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.test.entity.Universite;

import java.util.List;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Long> {

}
