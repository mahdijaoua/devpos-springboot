package tn.esprit.test.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.test.entity.Chambre;
import tn.esprit.test.service.IChambreService;

import java.util.List;
@Tag(name = "Gestion Chambre")
@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
public class ChambreRestController {
    IChambreService chambreService;
    // http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
    @Operation(description = "récupérer toutes les chambres de la base de données")
    @GetMapping("/retrieve-all-chambres")
    public List<Chambre> getChambres() {
        List<Chambre> listChambres = chambreService.retrieveAllChambres();
        return listChambres;
    }
    // http://localhost:8089/tpfoyer/chambre/retrieve-chambre/8
    @GetMapping("/retrieve-chambre/{chambre-id}")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chId) {
        Chambre chambre = chambreService.retrieveChambre(chId);
        return chambre;
    }
    // http://localhost:8089/tpfoyer/chambre/add-chambre
    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }
    // http://localhost:8089/tpfoyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-chambre/{chambre-id}")
    public void removeChambre(@PathVariable("chambre-id") Long chId) {
        chambreService.removeChambre(chId);
    }
    // http://localhost:8089/tpfoyer/chambre/modify-chambre
    @PutMapping("/modify-chambre")
    public Chambre modifyChambre(@RequestBody Chambre c) {
        Chambre chambre = chambreService.modifyChambre(c);
        return chambre;
    }


    // http://localhost:8089/tpfoyer/affectation-chambre-a-bloc/{chambre-id}/{bloc-id}
    @PutMapping("/affectation-chambre-a-bloc/{chambre-id}/{bloc-id}")
    public Chambre affecterChambreABloc(@PathVariable("chambre-id") Long chambreId, @PathVariable("bloc-id") Long blocId){
        return chambreService.affecterChambreABloc(chambreId, blocId);
    }


    @GetMapping("/chercher/{cin}")
    public Chambre findChambreByEtudiantCIN(@PathVariable ("cin") long cin) {
        return chambreService.findChambreByEtudiantCIN(cin);
//        if (chambre != null) {
//            return Null;
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }
}