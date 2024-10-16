package tn.esprit.tpfoyer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class EtudiantServiceImplTEST {
    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    EtudiantServiceImpl etudiantService;

    // Creating a sample Etudiant object for testing
    Etudiant etudiant1 = new Etudiant(1L, "John", "Doe", 123456789, new Date(), new HashSet<>());

    // Creating a list of Etudiants for testing
    List<Etudiant> listEtudiants = new ArrayList<Etudiant>() {
        {
            add(new Etudiant(2L, "Alice", "Smith", 987654321, new Date(), new HashSet<>()));
            add(new Etudiant(3L, "Bob", "Johnson", 112233445, new Date(), new HashSet<>()));
        }
    };

    @Test
    public void testRetrieveAllEtudiants() {
        // Mocking the behavior of the repository
        Mockito.when(etudiantRepository.findAll()).thenReturn(listEtudiants);

        // Retrieving all Etudiants
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        // Asserting that the retrieved list is not null and has expected size
        Assertions.assertNotNull(etudiants);
        Assertions.assertEquals(2, etudiants.size());
    }

    @Test
    public void testRetrieveEtudiant() {
        // Mocking the behavior of the repository
        Mockito.when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant1));

        // Retrieving the Etudiant
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1L);

        // Asserting that the retrieved Etudiant is not null
        Assertions.assertNotNull(retrievedEtudiant);
        Assertions.assertEquals("John", retrievedEtudiant.getNomEtudiant());
    }

    @Test
    public void testAddEtudiant() {
        // Mocking the behavior of the repository
        Mockito.when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        // Adding the Etudiant
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant1);

        // Asserting that the added Etudiant is not null and matches the expected object
        Assertions.assertNotNull(addedEtudiant);
        Assertions.assertEquals("John", addedEtudiant.getNomEtudiant());
    }

    @Test
    public void testRemoveEtudiant() {
        // Mocking the behavior of the repository
        Mockito.doNothing().when(etudiantRepository).deleteById(1L);

        // Removing the Etudiant
        etudiantService.removeEtudiant(1L);

        // Verifying that the repository's deleteById method was called
        Mockito.verify(etudiantRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testModifyEtudiant() {
        // Mocking the behavior of the repository
        Mockito.when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant1));
        Mockito.when(etudiantRepository.save(etudiant1)).thenReturn(etudiant1);

        // Modifying the Etudiant
        etudiant1.setNomEtudiant("UpdatedName");
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant1);

        // Asserting that the modified Etudiant is not null and matches the expected updates
        Assertions.assertNotNull(modifiedEtudiant);
        Assertions.assertEquals("UpdatedName", modifiedEtudiant.getNomEtudiant());
    }

    @Test
    public void testRecupererEtudiantParCin() {
        // Mocking the behavior of the repository
        Mockito.when(etudiantRepository.findEtudiantByCinEtudiant(123456789)).thenReturn(etudiant1);

        // Retrieving the Etudiant by CIN
        Etudiant retrievedEtudiant = etudiantService.recupererEtudiantParCin(123456789);

        // Asserting that the retrieved Etudiant is not null
        Assertions.assertNotNull(retrievedEtudiant);
        Assertions.assertEquals("John", retrievedEtudiant.getNomEtudiant());
    }

}
