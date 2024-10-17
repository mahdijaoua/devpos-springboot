package tn.esprit.test.service;
import tn.esprit.test.entity.Bloc;
import java.util.List;
public interface IBlocService {
    public List<Bloc> retrieveAllBlocs();
    public Bloc retrieveBloc(Long idBloc);
    public Bloc addBloc(Bloc c);
    public void removeBloc(Long idBloc);
    public Bloc modifyBloc(Bloc chambre);


    public Bloc DesaffecterBlocFoyer(Long blocID);

    public void affecterBlocFoyer(Long blocID, Long foyerID);
    public List<Bloc> findAllUnassignedBlocs();


}