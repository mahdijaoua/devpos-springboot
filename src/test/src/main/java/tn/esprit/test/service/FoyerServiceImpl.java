package tn.esprit.test.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.test.entity.Bloc;
import tn.esprit.test.entity.Foyer;
import tn.esprit.test.repository.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {
    FoyerRepository foyerRepository;
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }
    public Foyer retrieveFoyer(Long foyerId) {
        return foyerRepository.findById(foyerId).get();
    }
    public Foyer addFoyer(Foyer c) {
        return foyerRepository.save(c);
    }
    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }
    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    public Foyer addBlocAndFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

}