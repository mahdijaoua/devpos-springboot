package tn.esprit.tpfoyer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)

class BlocServiceImplMockTest {

    @Mock
    BlocRepository blocRepository;

    @InjectMocks
    BlocServiceImpl blocService;

    Bloc bloc1= new Bloc(1, "BlocA", 20);
    List<Bloc> listBlocs = new ArrayList<Bloc>() {
        {
            add(new Bloc(2, "BlocB", 20));
            add(new Bloc(3, "BlocC", 20));
        }
    };

    @Test
    public void testRetrieveAllBlocs() {
        // Mocking the behavior of the repository
        Mockito.when(blocRepository.findAll()).thenReturn(listBlocs);


        List<Bloc> blocs = blocService.retrieveAllBlocs();

        // Asserting that the retrieved list is not null and has expected size
        Assertions.assertNotNull(blocs);
        Assertions.assertEquals(2, blocs.size());
    }

    @Test
    public void testRetrieveBloc() {
        // Mocking the behavior of the repository
        Mockito.when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc1));


        Bloc retrievedBloc = blocService.retrieveBloc(1L);


        Assertions.assertNotNull(retrievedBloc);
        Assertions.assertEquals("BlocA", retrievedBloc.getNomBloc());
    }

    @Test
    public void testAddBloc() {
        // Mocking the behavior of the repository
        Mockito.when(blocRepository.save(bloc1)).thenReturn(bloc1);


        Bloc addedBloc = blocService.addBloc(bloc1);


        Assertions.assertNotNull(addedBloc);
        Assertions.assertEquals("BlocA", addedBloc.getNomBloc());
    }

    @Test
    public void testRemoveBloc() {
        // Mocking the behavior of the repository
        Mockito.doNothing().when(blocRepository).deleteById(1L);


        blocService.removeBloc(1L);

        // Verifying that the repository's deleteById method was called
        Mockito.verify(blocRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testModifyBloc() {
        // Mocking the behavior of the repository
        Mockito.when(blocRepository.findById(1L)).thenReturn(Optional.of(bloc1));
        Mockito.when(blocRepository.save(bloc1)).thenReturn(bloc1);


        bloc1.setNomBloc("UpdatedName");
        Bloc modifiedBloc = blocService.modifyBloc(bloc1);


        Assertions.assertNotNull(modifiedBloc);
        Assertions.assertEquals("UpdatedName", modifiedBloc.getNomBloc());
    }

  //  @Test
  //  public void testRecupererBlocParCin() {
        // Mocking the behavior of the repository
   //     Mockito.when(blocRepository.findEtudiantByCinEtudiant(123456789)).thenReturn(etudiant1);

        // Retrieving the Etudiant by CIN
     //   Etudiant retrievedEtudiant = etudiantService.recupererEtudiantParCin(123456789);

        // Asserting that the retrieved Etudiant is not null
      //  Assertions.assertNotNull(retrievedEtudiant);
       // Assertions.assertEquals("John", retrievedEtudiant.getNomEtudiant());
    }

}
