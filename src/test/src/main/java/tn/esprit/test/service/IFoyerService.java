package tn.esprit.test.service;
import tn.esprit.test.entity.Bloc;
import tn.esprit.test.entity.Foyer;
import java.util.List;
public interface IFoyerService {
    public List<Foyer> retrieveAllFoyers();
    public Foyer retrieveFoyer(Long idFoyer);
    public Foyer addFoyer(Foyer c);
    public void removeFoyer(Long idFoyer);
    public Foyer modifyFoyer(Foyer foyer);

    public Foyer addBlocAndFoyer(Foyer f);


}