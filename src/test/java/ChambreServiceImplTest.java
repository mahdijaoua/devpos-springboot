package tn.esprit.tpfoyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChambreServiceImplTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    private Chambre chambre;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101L);
        chambre.setTypeC(TypeChambre.SIMPLE);
    }

    @Test
    void testAddChambre() {
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);
        Chambre result = chambreService.addChambre(chambre);
        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRetrieveChambre() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));
        Chambre result = chambreService.retrieveChambre(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveAllChambres() {
        Chambre chambre2 = new Chambre();
        chambre2.setIdChambre(2L);
        chambre2.setNumeroChambre(102L);
        chambre2.setTypeC(TypeChambre.DOUBLE);

        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre, chambre2));
        List<Chambre> result = chambreService.retrieveAllChambres();
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    void testModifyChambre() {
        when(chambreRepository.save(any(Chambre.class))).thenReturn(chambre);
        Chambre result = chambreService.modifyChambre(chambre);
        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    void testRemoveChambre() {
        doNothing().when(chambreRepository).deleteById(1L);
        chambreService.removeChambre(1L);
        verify(chambreRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRecupererChambresSelonTyp() {
        Chambre chambre2 = new Chambre();
        chambre2.setIdChambre(2L);
        chambre2.setNumeroChambre(102L);
        chambre2.setTypeC(TypeChambre.SIMPLE);

        when(chambreRepository.findAllByTypeC(TypeChambre.SIMPLE)).thenReturn(Arrays.asList(chambre, chambre2));
        List<Chambre> result = chambreService.recupererChambresSelonTyp(TypeChambre.SIMPLE);
        assertEquals(2, result.size());
        verify(chambreRepository, times(1)).findAllByTypeC(TypeChambre.SIMPLE);
    }

    @Test
    void testTrouverchambreSelonEtudiant() {
        when(chambreRepository.trouverChselonEt(12345L)).thenReturn(chambre);
        Chambre result = chambreService.trouverchambreSelonEtudiant(12345L);
        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).trouverChselonEt(12345L);
    }
}
