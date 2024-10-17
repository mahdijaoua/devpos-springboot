package tn.esprit.test.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.test.entity.Chambre;
import tn.esprit.test.entity.Bloc;

import tn.esprit.test.repository.BlocRepository;
import tn.esprit.test.repository.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ChambreServiceImpl implements IChambreService {
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }
    public Chambre retrieveChambre(Long chambreId) {
        return chambreRepository.findById(chambreId).get();
    }
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }
    public void removeChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }
    public Chambre modifyChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }


    public Chambre affecterChambreABloc(Long chambreId, Long blocId) {
        Chambre chambre = chambreRepository.findById(chambreId).get();
        Bloc bloc = blocRepository.findById(blocId).get();
        chambre.setBloc(bloc);
        return chambreRepository.save(chambre);
            }

    public Chambre findChambreByEtudiantCIN(long cin) {
        return chambreRepository.findChambreByEtudiantCIN(cin);
    }

    }



