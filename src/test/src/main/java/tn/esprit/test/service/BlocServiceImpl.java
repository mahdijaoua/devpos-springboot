package tn.esprit.test.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.test.entity.Bloc;
import tn.esprit.test.entity.Chambre;
import tn.esprit.test.entity.Foyer;
import tn.esprit.test.repository.BlocRepository;
import tn.esprit.test.repository.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BlocServiceImpl implements IBlocService {
    BlocRepository blocRepository;
    FoyerRepository foyerRepository;
    public List<Bloc> retrieveAllBlocs() {
        return blocRepository.findAll();
    }
    public Bloc retrieveBloc(Long blocId) {
        return blocRepository.findById(blocId).get();
    }
    public Bloc addBloc(Bloc c) {
        return blocRepository.save(c);
    }
    public void removeBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }
    public Bloc modifyBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }



    public void affecterBlocFoyer(Long blocID, Long foyerID) {
        Bloc b = blocRepository.findById(blocID).get();
        Foyer f = foyerRepository.findById(foyerID).get();
        b.setFoyer(f);
        blocRepository.save(b);
    }

    public Bloc DesaffecterBlocFoyer(Long blodID) {
        Bloc b = blocRepository.findById(blodID).get();
        b.setFoyer(null);
        return blocRepository.save(b);
    }

    public List<Bloc> findAllUnassignedBlocs() {
        return blocRepository.findAllByFoyerIsNull();
    }


//    public Chambre affecterChambreABloc(Long chambreId, Long blocId) {
//        Chambre chambre = chambreRepository.findById(chambreId).get();
//        Bloc bloc = blocRepository.findById(blocId).get();
//        chambre.setBloc(bloc);
//        return chambreRepository.save(chambre);
//    }

}