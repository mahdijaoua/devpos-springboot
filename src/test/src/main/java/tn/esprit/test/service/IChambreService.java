package tn.esprit.test.service;
import tn.esprit.test.entity.Chambre;
import java.util.List;
public interface IChambreService {
    public List<Chambre> retrieveAllChambres();
    public Chambre retrieveChambre(Long idChambre);
    public Chambre addChambre(Chambre c);
    public void removeChambre(Long idChambre);
    public Chambre modifyChambre(Chambre chambre);

    public Chambre affecterChambreABloc(Long idChambre,Long idBloc);

    public Chambre findChambreByEtudiantCIN(long cin);
    }

