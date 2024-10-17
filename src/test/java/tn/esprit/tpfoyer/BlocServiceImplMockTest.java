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

    // Instances fictives
    Foyer foyer1 = new Foyer();
    Bloc bloc1 = new Bloc(1L, "BlocA", 20, foyer1, new HashSet<>());
    List<Bloc> listBlocs = new ArrayList<>() {{
        add(new Bloc(2L, "BlocB", 30, foyer1, new HashSet<>()));
        add(new Bloc(3L, "BlocC", 40, foyer1, new HashSet<>()));
    }};

    @Test
    public void testRetrieveAllBlocs() {
        Mockito.when(blocRepository.findAll()).thenReturn(listBlocs);
        List<Bloc> blocs = blocService.retrieveAllBlocs();

        Assertions.assertNotNull(blocs);
        Assertions.assertEquals(2, blocs.size());
        Assertions.assertEquals("BlocB", blocs.get(0).getNomBloc());
    }

    @Test
    public void testRetrieveBloc() {
        Mockito.when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc1));
        Bloc retrievedBloc = blocService.retrieveBloc(1L);

        Assertions.assertNotNull(retrievedBloc);
        Assertions.assertEquals("BlocA", retrievedBloc.getNomBloc());
        Assertions.assertEquals(foyer1, retrievedBloc.getFoyer());
    }

    @Test
    public void testAddBloc() {
        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(bloc1);
        Bloc addedBloc = blocService.addBloc(bloc1);

        Assertions.assertNotNull(addedBloc);
        Assertions.assertEquals("BlocA", addedBloc.getNomBloc());
        Assertions.assertEquals(foyer1, addedBloc.getFoyer());
    }

    @Test
    public void testRemoveBloc() {
        Mockito.doNothing().when(blocRepository).deleteById(1L);
        blocService.removeBloc(1L);

        Mockito.verify(blocRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testModifyBloc() {
        // Simulation pour la récupération et la modification d'un bloc
        Mockito.lenient().when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc1)); // Utilisation de lenient pour ignorer l'avertissement
        Mockito.when(blocRepository.save(Mockito.any(Bloc.class))).thenReturn(bloc1);

        // Modification du bloc
        bloc1.setNomBloc("UpdatedName");
        Bloc modifiedBloc = blocService.modifyBloc(bloc1);

        // Assertions pour vérifier la modification
        Assertions.assertNotNull(modifiedBloc);
        Assertions.assertEquals("UpdatedName", modifiedBloc.getNomBloc());
        Assertions.assertEquals(foyer1, modifiedBloc.getFoyer());

        // Vérification de l'appel au repository
        Mockito.verify(blocRepository, Mockito.times(1)).save(Mockito.any(Bloc.class));
    }
}
