package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BlocServiceImplMockTest {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;

    // Création de quelques instances fictives pour les tests
    Foyer foyer1 = new Foyer();
    Bloc bloc1 = new Bloc(1L, "BlocA", 20, foyer1, new HashSet<>());
    List<Bloc> listBlocs = new ArrayList<>() {
        {
            add(new Bloc(2L, "BlocB", 30, foyer1, new HashSet<>()));
            add(new Bloc(3L, "BlocC", 40, foyer1, new HashSet<>()));
        }
    };

    @Test
    public void testRetrieveAllBlocs() {
        // Simulation du comportement du repository
        Mockito.when(blocRepository.findAll()).thenReturn(listBlocs);

        // Exécution du service
        List<Bloc> blocs = blocService.retrieveAllBlocs();

        // Assertions sur les résultats
        Assertions.assertNotNull(blocs);
        Assertions.assertEquals(2, blocs.size());
        Assertions.assertEquals("BlocB", blocs.get(0).getNomBloc());
    }

    @Test
    public void testRetrieveBloc() {
        // Simulation du comportement du repository pour un bloc spécifique
        Mockito.when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc1));

        // Exécution du service
        Bloc retrievedBloc = blocService.retrieveBloc(1L);

        // Assertions sur les résultats
        Assertions.assertNotNull(retrievedBloc);
        Assertions.assertEquals("BlocA", retrievedBloc.getNomBloc());
        Assertions.assertEquals(foyer1, retrievedBloc.getFoyer()); // Vérification du foyer associé
    }

    @Test
    public void testAddBloc() {
        // Simulation du comportement du repository lors de l'ajout d'un bloc
        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(bloc1); // Utiliser Mockito.any

        // Exécution du service
        Bloc addedBloc = blocService.addBloc(bloc1);

        // Assertions sur les résultats
        Assertions.assertNotNull(addedBloc);
        Assertions.assertEquals("BlocA", addedBloc.getNomBloc());
        Assertions.assertEquals(foyer1, addedBloc.getFoyer()); // Vérification du foyer associé
    }

    @Test
    public void testRemoveBloc() {
        // Simulation du comportement du repository pour la suppression
        Mockito.doNothing().when(blocRepository).deleteById(1L);

        // Exécution du service
        blocService.removeBloc(1L);

        // Vérification que la méthode de suppression du repository a été appelée une fois
        Mockito.verify(blocRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testModifyBloc() {
        // Simulation du comportement du repository lors de la modification
        Mockito.when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc1));
        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(bloc1); // Utiliser Mockito.any

        // Modification du bloc
        bloc1.setNomBloc("UpdatedName");
        Bloc modifiedBloc = blocService.modifyBloc(bloc1);

        // Assertions sur les résultats
        Assertions.assertNotNull(modifiedBloc);
        Assertions.assertEquals("UpdatedName", modifiedBloc.getNomBloc());
        Assertions.assertEquals(foyer1, modifiedBloc.getFoyer()); // Vérification du foyer associé

        // Vérification que save a été appelé
        Mockito.verify(blocRepository, Mockito.times(1)).save(bloc1);
    }
}
