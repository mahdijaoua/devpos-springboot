package tn.esprit.tpfoyer;

import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class ChambreServiceImplTest {

    @InjectMocks
    private ChambreServiceImpl chambreService; // The class you want to test

    @Mock
    private ChambreRepository chambreRepository; // Mocked dependency

    private Chambre chambre;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        chambre = new Chambre();
        chambre.setId(1L);
        chambre.setName("Room 101");
    }

    @Test
    public void testFindChambreByIdSuccess() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.findById(1L);
        assertNotNull(result);
        assertEquals("Room 101", result.getName());
        verify(chambreRepository, times(1)).findById(1L); // Ensure findById was called once
    }

    @Test
    public void testFindChambreByIdNotFound() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.empty());

        Chambre result = chambreService.findById(1L);
        assertNull(result);
        verify(chambreRepository, times(1)).findById(1L); // Ensure findById was called once
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveChambreInvalid() {
        doThrow(new IllegalArgumentException("Invalid Chambre")).when(chambreRepository).save(any(Chambre.class));

        chambreService.saveChambre(new Chambre()); // This should throw an exception
    }
}
